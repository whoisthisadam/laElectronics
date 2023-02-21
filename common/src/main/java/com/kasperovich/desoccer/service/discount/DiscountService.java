package com.kasperovich.desoccer.service.discount;

import com.kasperovich.desoccer.models.Discount;

import java.util.List;

public interface DiscountService {

    List<Discount> findAll();

    Discount createDiscount(Discount discount);

    Discount deleteDiscount(Long id);


}
