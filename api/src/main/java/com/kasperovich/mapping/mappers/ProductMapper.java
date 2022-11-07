package com.kasperovich.mapping.mappers;

import com.kasperovich.dto.product.ProductCreateDto;
import com.kasperovich.dto.product.ProductGetDto;
import com.kasperovich.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    public Product toEntity(ProductCreateDto productCreateDto);

    public ProductGetDto toDto(Product product);


}
