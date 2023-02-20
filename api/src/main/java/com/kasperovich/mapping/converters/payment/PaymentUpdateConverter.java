package com.kasperovich.mapping.converters.payment;

import com.kasperovich.dto.payment.PaymentCreateDto;
import com.kasperovich.models.Edit;
import com.kasperovich.models.Payment;
import com.kasperovich.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentUpdateConverter {

    PaymentRepository paymentRepository;

    public Payment convert(PaymentCreateDto paymentCreateDto, Long id){

        Payment payment=paymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        payment.setProvider(Optional.ofNullable(paymentCreateDto.getProvider()).orElse(payment.getProvider()));

        payment.setEditData(new Edit(payment.getEditData().getCreationDate(), new Timestamp(new Date().getTime())));

        payment.setAmount(payment.getOrder().getTotal());

        return payment;
    }

}
