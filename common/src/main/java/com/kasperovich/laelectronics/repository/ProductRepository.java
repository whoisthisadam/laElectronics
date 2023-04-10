package com.kasperovich.laelectronics.repository;

import com.kasperovich.laelectronics.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
