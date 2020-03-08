package com.javatech.product.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@FieldDefaults(level= AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

     @Id
     @GeneratedValue
     int id;
     String nom;
     int prix;



     public ProductDto toDto(){
          if(id == 0){
               return null;
          }
          return ProductDto.builder().id(this.id).nom(nom).prix(prix).build();
     }


}
