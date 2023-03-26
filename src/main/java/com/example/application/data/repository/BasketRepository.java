package com.example.application.data.repository;

import java.util.List;

import com.example.application.data.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("select count(b) from Basket b where b.user_id = ?1")
    long countByUser_id(Long user_id);


     @Query("select sum(b.quantity) from Basket b where b.user_id = ?1")
     long sumQuantityByUser_id(Long user_id);

    @Transactional
    @Modifying
    @Query("delete from Basket b where b.user_id = ?1")
    void deleteByUser_id(Long user_id);

    //find all basket for user id
    @Query("select c from Basket c " +
            "where c.user_id = :user_id")
    List<Basket> findAllByUserId(@Param("user_id") Long user_id);

    //find all basket by user id and product id
    @Query("select c from Basket c " +
            "where c.user_id = :user_id and c.product.id = :product_id")
    Basket findByUserIdAndProductId(@Param("user_id") Long user_id, @Param("product_id") Long product_id);

}
