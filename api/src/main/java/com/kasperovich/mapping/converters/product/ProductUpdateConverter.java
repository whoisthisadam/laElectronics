package com.kasperovich.mapping.converters.product;

import com.kasperovich.dto.product.ProductCreateDto;
import com.kasperovich.dto.users.UserCreateDto;
import com.kasperovich.models.Edit;
import com.kasperovich.models.Product;
import com.kasperovich.models.User;
import com.kasperovich.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductUpdateConverter implements Converter<ProductCreateDto, Product> {

    ProductRepository productRepository;


    @Override
    public Product convert(ProductCreateDto productCreateDto) {
        return null;
    }

    public Product doConvert(ProductCreateDto productCreateDto, Long id) throws EntityNotFoundException {
        Product product=new Product();
        product.setId(id);

        product.setName(
                Optional.ofNullable(
                                productCreateDto.getName())
                        .orElse(productRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getName()
                        )
        );
        product.setBrand(
                Optional.ofNullable(
                                productCreateDto.getBrand())
                        .orElse(productRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getBrand()
                        )
        );
        product.setPrice(
                Optional.ofNullable(
                                productCreateDto.getPrice())
                        .orElse(productRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getPrice()
                        )

        );
        product.setStatus(
                Optional.ofNullable(
                                productCreateDto.getStatus())
                        .orElse(productRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getStatus()
                        )
        );
        product.setEditData(productRepository.findById(id).orElseThrow(EntityNotFoundException::new).getEditData());
        return product;
    }
}
