package com.croix.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.croix.shop.model.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long>{

}
