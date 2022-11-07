package com.kasperovich.mapping.converters.payment;

import com.kasperovich.dto.payment.PaymentCreateDto;
import com.kasperovich.enums.PaymentStatus;
import com.kasperovich.models.Edit;
import com.kasperovich.models.Order;
import com.kasperovich.models.Payment;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentCreateConverter {

    public Payment convert(PaymentCreateDto paymentCreateDto, Order order){
        Payment payment=new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotal());
        payment.setProvider(paymentCreateDto.getProvider());
        payment.setStatus(PaymentStatus.IN_PROGRESS);
        payment.setEditData(new Edit(new Timestamp(new Date().getTime()), null));
        payment.setStatus(PaymentStatus.values()[new Random().nextInt(4)]);
        return payment;
    }

}
