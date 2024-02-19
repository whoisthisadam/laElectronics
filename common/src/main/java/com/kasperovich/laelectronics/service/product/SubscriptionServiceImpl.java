package com.kasperovich.laelectronics.service.product;

import com.kasperovich.laelectronics.exception.NotDeletableStatusException;
import com.kasperovich.laelectronics.models.Edit;
import com.kasperovich.laelectronics.models.Subscription;
import com.kasperovich.laelectronics.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll().stream().filter(x -> !x.getIsDeleted()).collect(Collectors.toList());
    }

    @Override
    public Subscription createProduct(@Valid Subscription subscription) {
        subscription.setEditData(new Edit(new Timestamp(new Date().getTime()), null));
        subscription.setIsAvailable(true);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription deleteProduct(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        subscription.setIsDeleted(true);
        return subscriptionRepository.save(subscription);
//        if(product.getStatus()== ProductStatus.OUT_OF_STOCK){
//            product.setIsDeleted(true);
//            return productRepository.save(product);
//        }
//        else throw new NotDeletableStatusException(
//                "Unable to delete product with status "+product.getStatus().toString()
//        );
    }

    @Override
    public Subscription updateProduct(@Valid Subscription subscription) {
        subscription.setEditData(
                new Edit(
                        subscription.getEditData().getCreationDate(), new Timestamp(new Date().getTime())
                )
        );
        return subscriptionRepository.save(subscription);
    }
}
