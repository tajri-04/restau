package com.javatech.mail.dao.product;

import com.javatech.product.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("productRepositoryJdbc")
public class ProductRepositoryJdbc implements  ProductRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(
                "select * from PRODUCT ",
                (rs, rowNum) ->
                        new Product(
                                rs.getInt("id"),
                                rs.getString("nom"),
                                rs.getInt("prix")
                        )
        );
    }


    @Override
    public Optional<Product> findById(int id) {
        Optional<Product> result = Optional.empty();
        try{
             result = jdbcTemplate.queryForObject(
                    "select * from PRODUCT where id = ?",
                    new Object[]{id},
                    (rs, rowNum) ->
                            Optional.of(new Product(
                                    rs.getInt("id"),
                                    rs.getString("nom"),
                                    rs.getInt("prix")
                            ))
            );
        }
        catch (EmptyResultDataAccessException e){
            return result;
        }
        return result;
    }

    @Override
    public Product save(Product product) {
        int proId = jdbcTemplate.update(
                "insert into PRODUCT (nom, prix) values(?,?)",
                product.getNom()+" jdbc", product.getPrix());
        product.setId(proId);
        return product;
    }


}
