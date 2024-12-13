package com.croix.shop.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order;

    private LocalDate create_date;

    @NotNull(message = "Valor id_user no puede ser nulo")
    @Min(value = 0, message = "Valor id_user no puede ser negativo")
    @Max(value = 99999999, message = "Valor id_user no puede ser superior a 99999999")
    private Long id_user;

    @NotNull(message = "Valor total no puede ser nulo")
    @Max(value = 999999999, message = "Valor total no puede ser superior a 999999999")
    private Long total;

    @NotNull(message = "Valor status no puede ser nulo")
    @Size(min = 1, max = 50, message = "name debe tener entre 1 y 50 caracteres")
    private String status;

    protected Orders() {
    }

    private Orders(Builder builder) {
        this.id_order = builder.id_order;
        this.create_date = builder.create_date;
        this.id_user = builder.id_user;
        this.total = builder.total;
        this.status = builder.status;
    }

    public Long getId_order() { return id_order; }
    public LocalDate getCreate_date() { return create_date; }
    public Long getId_user() { return id_user; }
    public Long getTotal() { return total; }
    public String getStatus() { return status; }

    public Builder toBuilder() {
        return new Builder()
                .setId_order(this.id_order)
                .setCreate_date(this.create_date)
                .setId_user(this.id_user)
                .setTotal(this.total)
                .setStatus(this.status);
    }

    public static class Builder {
        private Long id_order;
        private LocalDate create_date;
        private Long id_user;
        private Long total;
        private String status;

        public Orders build() {
            return new Orders(this);
        }

        public Builder setId_order(Long id_order) {
            this.id_order = id_order;
            return this;
        }

        public Builder setCreate_date(LocalDate create_date) {
            this.create_date = create_date;
            return this;
        }

        public Builder setId_user(Long id_user) {
            this.id_user = id_user;
            return this;
        }

        public Builder setTotal(Long total) {
            this.total = total;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }
    }

}
