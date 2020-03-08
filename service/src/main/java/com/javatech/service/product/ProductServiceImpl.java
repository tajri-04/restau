package com.javatech.service.product;

import com.javatech.mail.dao.product.ProductRepository;
import com.javatech.product.dto.Product;
import com.javatech.product.dto.ProductDto;
import com.javatech.service.product.exceptions.NotFoundProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream().map(p -> p.toDto()).collect(Collectors.toList());

    }

    @Override
    public ProductDto ajouterProduit(ProductDto p) {
        Product createdProduit = productRepository.save(p.toModel());
         return createdProduit.toDto();
    }

    public ProductDto rechercheProduitParId(int id) throws NotFoundProductException {
        Product createdProduit = productRepository.findById(id).
                orElseThrow(() -> new NotFoundProductException("not found"));
        return createdProduit.toDto();
    }
}
