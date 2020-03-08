package com.javatech.mail.dao.product;

import com.javatech.product.dto.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public List<Product> findAll();

    public Optional<Product> findById(int id);

    public Product save(Product product);
}
