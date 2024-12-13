package com.croix.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.croix.shop.model.Products;
import com.croix.shop.repository.CatalogRepository;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    public List<Products> getAllProducts(){
        return catalogRepository.findAll();
    }

    public Optional<Products> getProductById(Long id){
        return catalogRepository.findById(id);
    }
}
