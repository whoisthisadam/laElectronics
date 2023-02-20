package com.kasperovich.service.discount;

import com.kasperovich.models.Discount;

import java.util.List;

public interface DiscountService {

    List<Discount> findAll();

    Discount createDiscount(Discount discount);

    Discount deleteDiscount(Long id);


}
