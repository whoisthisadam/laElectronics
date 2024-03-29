package com.kasperovich.laelectronics.api.mapping.converters.product;

import com.kasperovich.laelectronics.api.dto.product.ProductCreateDto;
import com.kasperovich.laelectronics.models.Category;
import com.kasperovich.laelectronics.models.Product;
import com.kasperovich.laelectronics.repository.CategoryRepository;
import com.kasperovich.laelectronics.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductUpdateConverter implements Converter<ProductCreateDto, Product> {

    ProductRepository productRepository;

    CategoryRepository categoryRepository;


    @Override
    public Product convert(ProductCreateDto productCreateDto) {
        return null;
    }

    public Product doConvert(ProductCreateDto productCreateDto, Long id) throws EntityNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with this id does not exist"));
        product.setId(id);

        if(productCreateDto.getCategory()!=null){
            String searchCatName=productCreateDto.getCategory().getName();
            Category category=categoryRepository.findCategoryByCategoryName(searchCatName)
                    .orElseThrow(()->new EntityNotFoundException("Category "+searchCatName+" not found"));
            product.setCategory(category);
        }
        product.setName(
                Optional.ofNullable(productCreateDto.getName()).orElse(product.getName())
        );
        product.setBrand(
                Optional.ofNullable(
                        productCreateDto.getBrand()).orElse(product.getBrand())
        );
        product.setPrice(
                Optional.ofNullable(productCreateDto.getPrice()).orElse(product.getPrice())
        );
        product.setStatus(
                Optional.ofNullable(
                                productCreateDto.getStatus())
                        .orElse(product.getStatus())
        );
        return product;
    }
}
