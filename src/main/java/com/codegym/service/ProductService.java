package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        int index = findProductById(id);
        return products.get(index);
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void remove(Long id) {
        int index = findProductById(id);
        products.remove(index);
    }

    private int findProductById(Long id) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> newProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(name)) {
                newProducts.add(product);
            }
        }
        return newProducts;
    }
}
