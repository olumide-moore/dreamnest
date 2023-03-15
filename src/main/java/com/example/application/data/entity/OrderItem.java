package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class OrderItem extends AbstractEntity {

    @NotNull
    private Long product_id;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    @PositiveOrZero
    private float current_price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Orders orders;

    

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

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
    public float getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(float current_price) {
        this.current_price = current_price;
    }
}
