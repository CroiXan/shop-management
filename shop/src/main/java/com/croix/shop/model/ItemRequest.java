package com.croix.shop.model;

public class ItemRequest {
    private Long id_order;
    private Long id_product;
    
    public Long getId_order() {
        return id_order;
    }
    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }
    public Long getId_product() {
        return id_product;
    }
    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }
}
