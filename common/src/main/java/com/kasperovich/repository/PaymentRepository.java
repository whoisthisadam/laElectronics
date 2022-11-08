package com.kasperovich.repository;

import com.kasperovich.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    public Payment findPaymentByOrderId(Long id);

}
