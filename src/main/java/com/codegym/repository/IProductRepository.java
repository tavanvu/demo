package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
//    void insertProductUsingProcedure(Product product);
}
