package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Basket extends AbstractEntity {


    @NotNull
    private Long user_id;

    @NotNull
    private Long product_id;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @Override
    public String toString() {
        return user_id + ": " + product_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}