package com.kasperovich.mapping.converters.discount;

import com.kasperovich.dto.discount.DiscountGetDto;
import com.kasperovich.mapping.mappers.ProductListMapper;
import com.kasperovich.mapping.mappers.UserListMapper;
import com.kasperovich.models.Discount;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountGetConverter implements Converter<Discount, DiscountGetDto> {

    UserListMapper userListMapper;

    ProductListMapper productListMapper;

    @Override
    public DiscountGetDto convert(Discount discount) {
        DiscountGetDto discountGetDto=new DiscountGetDto();
        discountGetDto.setId(discount.getId());
        discountGetDto.setName(discount.getName());
        discountGetDto.setPercent(discount.getDiscountPercent());
        return discountGetDto;
    }


}
