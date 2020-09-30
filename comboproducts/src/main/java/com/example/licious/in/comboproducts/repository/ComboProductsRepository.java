package com.example.licious.in.comboproducts.repository;


import com.example.licious.in.comboproducts.entity.ComboProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComboProductsRepository extends JpaRepository<ComboProductsEntity, Long> {
    List<ComboProductsEntity> findAll();
}