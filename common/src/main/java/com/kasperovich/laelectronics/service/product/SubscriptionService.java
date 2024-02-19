package com.kasperovich.laelectronics.service.product;

import com.kasperovich.laelectronics.exception.NotDeletableStatusException;
import com.kasperovich.laelectronics.models.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> findAll();

    Subscription createProduct(Subscription subscription);

    Subscription deleteProduct(Long id) throws NotDeletableStatusException;

    Subscription updateProduct(Subscription subscription);


}
