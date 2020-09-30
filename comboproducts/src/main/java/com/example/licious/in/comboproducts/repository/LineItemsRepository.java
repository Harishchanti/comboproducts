package com.example.licious.in.comboproducts.repository;


import com.example.licious.in.comboproducts.entity.LineItemsEntity;
import com.example.licious.in.comboproducts.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineItemsRepository extends JpaRepository<LineItemsEntity, Long> {
    List<LineItemsEntity> findAll();
}