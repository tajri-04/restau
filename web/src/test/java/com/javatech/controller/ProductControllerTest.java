package com.javatech.controller;

import com.javatech.product.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {Config.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/products");
    }



    @Test
    public void shouldReturnAllWhenCallAll() {
        ResponseEntity<ProductDto[]> response = template.getForEntity(base.toString(),
                ProductDto[].class);
        ProductDto[] products = response.getBody();
        assertNotNull(products);
       assertEquals(0, products.length);
    }

    @Test
    public void ajouterProduct() throws Exception {
        HttpEntity<ProductDto> request = new HttpEntity<>(ProductDto.builder().nom("test").prix(6).build());
        ResponseEntity<Void> result = template.postForEntity(base.toString(), request, Void.class);
        assertEquals(201,result.getStatusCodeValue());
    }

    @Test
    public void shouldReturnProductwhenisexist()  {
        HttpEntity<ProductDto> request = new HttpEntity<>(ProductDto.builder().nom("test").prix(6).build());
        template.postForEntity(base.toString(), request, Void.class);
        int idProduit = 1 ;
        ResponseEntity<ProductDto> result = template.getForEntity(base.toString()+"/" + idProduit, ProductDto.class);
        assertEquals(200,result.getStatusCodeValue());
        ProductDto p = result.getBody();
        assertEquals(idProduit,p.getId());
    }

    @Test
    public void shouldReturn404whennotexist()  {
        int idProduit = 10 ;
        ResponseEntity<ProductDto> result = template.getForEntity(base.toString()+"/" + idProduit, ProductDto.class);
        assertEquals(404,result.getStatusCodeValue());

    }


}