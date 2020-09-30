package com.example.licious.in.comboproducts.repository;


import com.example.licious.in.comboproducts.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
    List<ProductsEntity> findAll();

    @Query("FROM ProductsEntity o WHERE o.sku in (?1)")
    List<ProductsEntity> findByProductSku(List<String> skus);
}