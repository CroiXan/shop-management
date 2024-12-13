package com.croix.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.croix.shop.model.Orders;
import java.util.List;


public interface OrderRepository extends JpaRepository<Orders,Long>{

    @Query(value = "SELECT * FROM orders o WHERE o.id_user = ?1", nativeQuery = true)
    List<Orders> findById_user(@Param("id_order") Long id_user);
    
}
