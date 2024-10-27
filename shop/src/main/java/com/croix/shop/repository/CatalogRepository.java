package com.croix.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.croix.shop.model.Products;

public interface CatalogRepository extends JpaRepository<Products,Long>{

}
