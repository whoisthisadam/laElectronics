package com.kasperovich.repository;

import com.kasperovich.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    public Order findOrderByPaymentId(Long paymentId);


}
