package com.kasperovich.mapping.converters.order;

import com.kasperovich.dto.order.OrderGetDto;
import com.kasperovich.mapping.mappers.PaymentMapper;
import com.kasperovich.models.Order;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderGetConverter implements Converter<Order, OrderGetDto> {

    PaymentMapper paymentMapper;

    @Override
    public OrderGetDto convert(Order order) {
        OrderGetDto orderGetDto=new OrderGetDto();
        orderGetDto.setId(order.getId());
        orderGetDto.setUserId(order.getUser().getId());
        orderGetDto.setPayment(paymentMapper.toDto(order.getPayment()));
        orderGetDto.setTotal(order.getTotal());
        orderGetDto.setStatus(order.getOrderStatus());
        return orderGetDto;
    }
}
