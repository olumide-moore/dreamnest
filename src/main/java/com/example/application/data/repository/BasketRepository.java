package com.example.application.data.repository;

import java.util.List;

import com.example.application.data.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    //find all basket for user id
    @Query("select c from Basket c " +
            "where c.user_id = :user_id")
    List<Basket> findAllByUserId(@Param("user_id") Long user_id);

    //find all basket by user id and product id
    @Query("select c from Basket c " +
            "where c.user_id = :user_id and c.product_id = :product_id")
    Basket findByUserIdAndProductId(@Param("user_id") Long user_id, @Param("product_id") Long product_id);

}
