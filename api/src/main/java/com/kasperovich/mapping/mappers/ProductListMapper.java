package com.kasperovich.mapping.mappers;

import com.kasperovich.dto.product.ProductCreateDto;
import com.kasperovich.dto.product.ProductGetDto;
import com.kasperovich.models.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface ProductListMapper {

    public List<Product>toEntityList(List<ProductCreateDto> dtos);

    public List<ProductGetDto>toDto(List<Product>products);

}
