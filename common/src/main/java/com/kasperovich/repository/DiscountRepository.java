package com.kasperovich.repository;

import com.kasperovich.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Size;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findByDiscountPercent(@Size(min = 0, max = 100) Double discountPercent);

}
