package com.croix.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orderitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_orderitem;
    private Long id_order;
    private Long id_product;
    private String sku;
    private Long amount;
    
    public Long getId_orderitem() {
        return id_orderitem;
    }
    public void setId_orderitem(Long id_orderitem) {
        this.id_orderitem = id_orderitem;
    }
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
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
