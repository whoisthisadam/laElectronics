package com.kasperovich.mapping.mappers;

import com.kasperovich.dto.payment.PaymentGetDto;
import com.kasperovich.models.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    public PaymentGetDto toDto(Payment payment);

}
