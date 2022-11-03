package com.kasperovich.service.user;

import com.kasperovich.enums.Discounts;
import com.kasperovich.models.User;
import com.kasperovich.repository.AddressRepository;
import com.kasperovich.repository.DiscountRepository;
import com.kasperovich.repository.RoleRepository;
import com.kasperovich.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public User createUser(@NotNull User user) {

        if(user.getCredentials()!=null){
            user.setUserDiscount(discountRepository.findDiscountsByName(Discounts.LOGIN_DISCOUNT));
        }

        addressRepository.save(user.getAddress());

        return userRepository.save(user);
    }
}
