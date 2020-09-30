package com.example.licious.in.comboproducts.repository;


import com.example.licious.in.comboproducts.entity.ComboProductListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComboProductsListRepository extends JpaRepository<ComboProductListEntity, Long> {
    List<ComboProductListEntity> findAll();

    @Query("FROM ComboProductListEntity o WHERE o.comboProductsEntity.id = ?1")
    List<ComboProductListEntity> findAllByComboId(Long id);
}