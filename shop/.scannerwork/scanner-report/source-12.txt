package com.croix.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.croix.shop.model.Orderitem;
import java.util.List;


public interface OrderItemRepository extends JpaRepository<Orderitem,Long>{

    @Query(value = "SELECT * FROM orderitem oi WHERE oi.id_order = ?1", nativeQuery = true)
    List<Orderitem> findById_order(@Param("id_order") Long id_order);

    @Query(value = "SELECT * FROM orderitem oi WHERE oi.id_order = ?1 AND oi.id_product = ?2", nativeQuery = true)
    List<Orderitem> findById_orderAndId_product(@Param("id_order") Object id_order, @Param("id_product") Object id_product);
}
