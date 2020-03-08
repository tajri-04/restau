package com.javatech.mail.dao.product;

import com.javatech.product.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ProductRepositoryJpa implements  ProductRepository{

    ProductDao productDao;

    @Autowired
    public ProductRepositoryJpa(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public Product save(Product product) {
        product.setNom(product.getNom()+" JPA");
        return productDao.save(product);
    }
}
