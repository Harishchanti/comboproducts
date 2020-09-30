package com.example.licious.in.comboproducts.repository;


import com.example.licious.in.comboproducts.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findAll();

    @Query("FROM OrdersEntity o WHERE o.orderNumber = ?1")
    OrdersEntity findByOrderNumber(String orderNumber);
}