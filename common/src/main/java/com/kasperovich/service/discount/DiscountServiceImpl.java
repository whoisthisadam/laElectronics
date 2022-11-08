package com.kasperovich.service.discount;

import com.kasperovich.models.Discount;
import com.kasperovich.models.Edit;
import com.kasperovich.models.User;
import com.kasperovich.repository.DiscountRepository;
import com.kasperovich.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountServiceImpl implements DiscountService{

    DiscountRepository discountRepository;

    UserRepository userRepository;

    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll().stream().filter(x->!x.getIsDeleted()).collect(Collectors.toList());
    }

    @Override
    public Discount createDiscount(Discount discount) {
        discount.setEditData(new Edit(new Timestamp(new Date().getTime()), null));
        return discountRepository.save(discount);
    }

    @Override
    public Discount deleteDiscount(Long id) {
        Discount discount=discountRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No discount with this ID!"));
        discount.setIsDeleted(true);
        List<User>users=userRepository.findUsersByUserDiscount(discount);
        users.forEach(x->{
            x.setUserDiscount(null);
            userRepository.save(x);
            }
        );
        return discountRepository.save(discount);
    }
}
