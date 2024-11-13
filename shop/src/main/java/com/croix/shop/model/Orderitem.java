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

    private Orderitem(Builder builder) {
        this.id_orderitem = builder.id_orderitem;
        this.id_order = builder.id_order;
        this.id_product = builder.id_product;
        this.sku = builder.sku;
        this.amount = builder.amount;
    }

    protected Orderitem() {
    }

    public Long getId_orderitem() { return id_orderitem; }
    public Long getId_order() { return id_order; }
    public Long getId_product() { return id_product; }
    public String getSku() { return sku; }
    public Long getAmount() { return amount; }
 
    public Builder toBuilder() {
        return new Builder()
                .setId_orderitem(this.id_orderitem)
                .setId_order(this.id_order)
                .setId_product(this.id_product)
                .setSku(this.sku)
                .setAmount(this.amount);
    }

    public static class Builder { 
        private Long id_orderitem;
        private Long id_order;
        private Long id_product;
        private String sku;
        private Long amount;

        public Orderitem build() {     
            return new Orderitem(this);
        }

        public Builder setId_orderitem(Long id_orderitem) {     
            this.id_orderitem = id_orderitem;
            return this;
        }

        public Builder setId_order(Long id_order) {     
            this.id_order = id_order;
            return this;
        }

        public Builder setId_product(Long id_product) {     
            this.id_product = id_product;
            return this;
        }

        public Builder setSku(String sku) {     
            this.sku = sku;
            return this;
        }

        public Builder setAmount(Long amount) {     
            this.amount = amount;
            return this;
        }

    }

}

