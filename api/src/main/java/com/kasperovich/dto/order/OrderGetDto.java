package com.kasperovich.dto.order;

import com.kasperovich.dto.payment.PaymentGetDto;
import com.kasperovich.enums.OrderStatus;
import com.kasperovich.models.Payment;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = false)
public class OrderGetDto {

    Long id;

    Long userId;

    Long total;

    PaymentGetDto payment;

    OrderStatus status;

}
