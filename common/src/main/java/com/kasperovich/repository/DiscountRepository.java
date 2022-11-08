package com.kasperovich.repository;

import com.kasperovich.enums.Discounts;
import com.kasperovich.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findDiscountsByName(Discounts discount);

}
