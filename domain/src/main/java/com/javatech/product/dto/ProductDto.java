package com.javatech.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@FieldDefaults(level= AccessLevel.PRIVATE)
@Data
@Builder
public class ProductDto implements Serializable {
     int id;
     String nom;
     int prix;

     public Product toModel(){
          return Product.builder()
                  .id(id)
                  .nom(nom)
                  .prix(prix).build();
     }

}
