package com.example.maawa.repository;

import com.example.maawa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends JpaRepository<Product,Integer> {
    Product findProductById(Integer id);
    List<Product> findAllByStoreId(Integer storeId);
}
