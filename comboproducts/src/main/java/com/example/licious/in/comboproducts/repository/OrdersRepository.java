package com.example.licious.in.comboproducts.repository;


import com.example.licious.in.comboproducts.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findAll();
}