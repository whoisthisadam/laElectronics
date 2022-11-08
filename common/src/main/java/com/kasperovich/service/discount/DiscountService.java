package com.kasperovich.service.discount;

import com.kasperovich.models.Discount;

import java.util.List;

public interface DiscountService {

    public List<Discount> findAll();

    public Discount createDiscount(Discount discount);

    public Discount deleteDiscount(Long id);


}
