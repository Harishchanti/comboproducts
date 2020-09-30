package com.example.licious.in.comboproducts.repository;


import com.example.licious.in.comboproducts.entity.CatagoryEntity;
import com.example.licious.in.comboproducts.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatagoryRepository extends JpaRepository<CatagoryEntity, Long> {
    List<CatagoryEntity> findAll();
}