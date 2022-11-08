package com.kasperovich.dto.discount;

import com.kasperovich.enums.Discounts;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = false)
public class DiscountGetDto {

    Long id;

    Discounts name;

    Integer percent;

}
