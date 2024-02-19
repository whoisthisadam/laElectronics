package com.kasperovich.laelectronics.api.dto.order;

import com.kasperovich.laelectronics.api.dto.payment.PaymentGetDto;
import com.kasperovich.laelectronics.api.dto.subscription.SubscriptionGetDto;
import com.kasperovich.laelectronics.enums.OrderStatus;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class OrderGetDto {

    Long id;

    Long userId;

    Long total;

    PaymentGetDto payment;

    OrderStatus status;

    List<SubscriptionGetDto> products;

}
