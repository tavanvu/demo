package com.codegym.repository;

import com.codegym.model.Product;

import java.util.List;

public interface IProductRepository extends IGeneralRepository<Product> {
    List<Product> findByName(String name);
}
