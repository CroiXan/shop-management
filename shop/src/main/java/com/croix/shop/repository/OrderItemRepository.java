package com.croix.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.croix.shop.model.Orderitem;

public interface OrderItemRepository extends JpaRepository<Orderitem,Long>{

}
