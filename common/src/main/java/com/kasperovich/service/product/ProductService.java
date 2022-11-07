package com.kasperovich.service.product;

import com.kasperovich.exception.UnableToDeleteProductException;
import com.kasperovich.models.Product;

import java.util.List;

public interface ProductService {

    public List<Product>findAll();

    public Product createProduct(Product product);

    public Product deleteProduct(Long id) throws UnableToDeleteProductException;

    public Product updateProduct(Product product);


}
