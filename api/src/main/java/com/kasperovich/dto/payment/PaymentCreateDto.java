package com.kasperovich.dto.payment;

import com.kasperovich.enums.PaymentProviders;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = false)
public class PaymentCreateDto {

    PaymentProviders provider;

}
