package com.croix.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Orderitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_orderitem;

    @NotNull(message = "Valor id_order no puede ser nulo")
    @Min(value = 0, message = "Valor id_order no puede ser negativo")
    @Max(value = 99999999, message = "Valor id_order no puede ser superior a 99999999")
    private Long id_order;

    @NotNull(message = "Valor id_product no puede ser nulo")
    @Min(value = 0, message = "Valor id_product no puede ser negativo")
    @Max(value = 99999999, message = "Valor id_product no puede ser superior a 99999999")
    private Long id_product;

    @NotNull(message = "Valor SKU no puede ser nulo")
    @Size(min = 1, max = 100, message = "SKU debe tener entre 1 y 100 caracteres")
    private String sku;

    @NotNull(message = "Valor amount no puede ser nulo")
    @Min(value = 0, message = "Valor amount no puede ser negativo")
    @Max(value = 999999999, message = "Valor amount no puede ser superior a 999999999")
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
