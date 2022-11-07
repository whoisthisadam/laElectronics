package com.kasperovich.service.product;

import com.kasperovich.enums.ProductStatus;
import com.kasperovich.exception.NotDeletableStatusException;
import com.kasperovich.models.Edit;
import com.kasperovich.models.Product;
import com.kasperovich.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().filter(x->!x.getIsDeleted()).collect(Collectors.toList());
    }

    @Override
    public Product createProduct(@NotNull Product product) {
        product.setEditData(new Edit(new Timestamp(new Date().getTime()), null));
        product.setStatus(ProductStatus.values()[new Random().nextInt(3)]);
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) throws NotDeletableStatusException {
        Product product=productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(product.getStatus()== ProductStatus.OUT_OF_STOCK){
            product.setIsDeleted(true);
            return productRepository.save(product);
        }
        else throw new NotDeletableStatusException(
                "Unable to delete product with status "+product.getStatus().toString()
        );
    }

    @Override
    public Product updateProduct(Product product) {
        product.setEditData(
                new Edit(
                        product.getEditData().getCreationDate(), new Timestamp(new Date().getTime())
                )
        );
        return productRepository.save(product);
    }
}
