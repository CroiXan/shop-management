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
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    @NotNull(message = "Valor SKU no puede ser nulo")
    @Size(min = 1, max = 100, message = "SKU debe tener entre 1 y 100 caracteres")
    private String sku;

    @NotNull(message = "Valor name no puede ser nulo")
    @Size(min = 1, max = 100, message = "name debe tener entre 1 y 100 caracteres")
    private String name;

    @Min(value = 0, message = "Valor price no puede ser negativo")
    @Max(value = 999999999, message = "Valor price no puede ser superior a 999999999")
    private Long price;

    @Min(value = 0, message = "Valor discount no puede ser negativo")
    @Max(value = 100, message = "Valor discount debe estar entre 0 y 100")
    private int discount;

    @NotNull(message = "Valor category no puede ser nulo")
    @Size(min = 1, max = 100, message = "category debe tener entre 1 y 100 caracteres")
    private String category;

    @NotNull(message = "Valor description no puede ser nulo")
    @Size(min = 1, max = 1000, message = "description debe tener entre 1 y 1000 caracteres")
    private String description;

    @Min(value = 0, message = "Valor de stock no puede ser negativo")
    @Max(value = 999999, message = "Valor stock no puede ser superior a 999999")
    private int stock;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
