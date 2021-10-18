package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class ProductRepository implements IProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        //Query tĩnh: "select * from product where id =" + id => tĩnh
        //Query động: "select * from product where id = ?" => set paramter cho id => động
        TypedQuery<Product> query = entityManager.createQuery("select p from Product as p", Product.class);
        return query.getResultList();
    }

    @Override
    public Product findById(Long id) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product as p where p.id = :id", Product.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void save(Product product) {
        if (product.getId() != null) {
            entityManager.merge(product);
        } else {
            entityManager.persist(product);
        }
    }

    @Override
    public void remove(Long id) {
        Product product = findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }

    @Override
    public void insertProductUsingProcedure(Product product) {
        Query query = entityManager.createNativeQuery("call insertProduct(?1,?2)");
        query.setParameter(1, product.getName());
        query.setParameter(2, product.getPrice());
        query.executeUpdate();
    }
}
