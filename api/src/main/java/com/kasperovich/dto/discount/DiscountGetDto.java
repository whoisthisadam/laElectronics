package com.kasperovich.dto.discount;

import com.kasperovich.dto.product.ProductGetDto;
import com.kasperovich.dto.users.UserGetDto;
import com.kasperovich.enums.Discounts;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Properties;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = false)
public class DiscountGetDto {

    Long id;

    Discounts name;

    Integer percent;

}
