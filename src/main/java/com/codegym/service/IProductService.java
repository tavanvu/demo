package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findByNameContaining(String name);
}
