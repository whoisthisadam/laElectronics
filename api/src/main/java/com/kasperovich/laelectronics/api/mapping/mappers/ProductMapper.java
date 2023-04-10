package com.kasperovich.laelectronics.api.mapping.mappers;

import com.kasperovich.laelectronics.api.dto.product.ProductCreateDto;
import com.kasperovich.laelectronics.api.dto.product.ProductGetDto;
import com.kasperovich.laelectronics.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDto productCreateDto);

    ProductGetDto toDto(Product product);


}
