package com.kasperovich.dto.payment;


import com.kasperovich.enums.PaymentProviders;
import com.kasperovich.enums.PaymentStatus;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = false)
public class PaymentGetDto {

    Long id;

    Long amount;

    PaymentProviders provider;

    PaymentStatus status;

}
