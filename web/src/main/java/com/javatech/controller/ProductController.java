package com.javatech.controller;


import com.javatech.product.dto.ProductDto;
import com.javatech.service.product.ProductService;
import com.javatech.service.product.exceptions.NotFoundProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
     Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> listProduits(){
        logger.info("my logs");
        System.out.println("my logseeeeeeeee");
       return ResponseEntity.ok(productService.getAllProduct());

    }


    //ajouter un produit
    @PostMapping
    public ResponseEntity<Void> ajouterProduit(@RequestBody ProductDto product) {
        ProductDto productAdded =  productService.ajouterProduit(product);
        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //Récupérer un produit par son Id
    @GetMapping(value="/{id}")
    public ResponseEntity<ProductDto> afficherUnProduit(@PathVariable int id) {
        ProductDto p = null;
        try {
            p = productService.rechercheProduitParId(id);
        } catch (NotFoundProductException e) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }


}
