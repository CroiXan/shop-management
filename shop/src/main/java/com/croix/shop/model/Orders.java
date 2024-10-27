package com.croix.shop.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order;
    private LocalDate create_date;
    private Long id_user;
    private Long total;
    private String status;
    
    public Long getId_order() {
        return id_order;
    }
    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }
    public LocalDate getCreate_date() {
        return create_date;
    }
    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }
    public Long getId_user() {
        return id_user;
    }
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
