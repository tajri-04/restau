package com.javatech.service.product;

import com.javatech.mail.dao.product.ProductRepository;
import com.javatech.product.dto.Product;
import com.javatech.product.dto.ProductDto;
import com.javatech.service.product.exceptions.NotFoundProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;



@SpringBootTest(classes = ProductServiceImpl.class)
class ProductServiceImplTest {

    @MockBean
    private ProductRepository productRepository;
    @InjectMocks
    ProductService productServiceImpl =new ProductServiceImpl(productRepository); ;

    @BeforeEach
    public  void  setup(){
       productServiceImpl = new ProductServiceImpl(productRepository);
    }
    @Test
    void shouldReturnListwhenCalllistService() {
        List<Product> products = new ArrayList<>() ;
        products.add(Product.builder().build());
        products.add(Product.builder().build());
        when(productRepository.findAll()).thenReturn(products);
        assertEquals(2,productServiceImpl.getAllProduct().size());
    }

    @Test
    void shouldReturnProductwhenCreated() {
        ProductDto pro = ProductDto.builder().id(1).nom("pro1").prix(13).build();
        when(productRepository.save(pro.toModel())).thenReturn(pro.toModel());
        ProductDto procreated = productServiceImpl.ajouterProduit(pro);
        assertEquals(pro,procreated);
    }

    @Test
    void shouldReturnProductwhenexist() throws NotFoundProductException {
        ProductDto pro = ProductDto.builder().id(1).nom("pro1").prix(13).build();
        when(productRepository.findById(1)).thenReturn(Optional.of(pro.toModel()));
        ProductDto prod = productServiceImpl.rechercheProduitParId(1);
        assertEquals(prod.getId(),1);
        assertEquals(prod.getNom(),"pro1");
        assertEquals(prod.getPrix(),13);
    }
    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        when(productRepository.findById(2)).thenReturn(Optional.empty());
        Exception exception = assertThrows(NotFoundProductException.class, () -> {
            productServiceImpl.rechercheProduitParId(2);
        });
    }
}