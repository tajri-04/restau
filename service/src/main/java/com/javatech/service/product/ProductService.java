package com.javatech.service.product;

import com.javatech.product.dto.ProductDto;
import com.javatech.service.product.exceptions.NotFoundProductException;

import java.util.List;

public interface ProductService {

    public List<ProductDto> getAllProduct();

    public ProductDto ajouterProduit(ProductDto p);

    public ProductDto rechercheProduitParId(int id) throws NotFoundProductException;
}
