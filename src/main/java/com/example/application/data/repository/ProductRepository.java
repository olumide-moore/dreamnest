package com.example.application.data.repository;

import java.util.List;

import com.example.application.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select c from Product c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.category) like lower(concat('%', :searchTerm, '%'))")
    List<Product> search(@Param("searchTerm") String searchTerm);


    //find all featured products
    @Query("select c from Product c " +
            "where c.featured = 1")
    List<Product> findAllFeaturedProduct();

    //find all products by categories
    @Query("select c from Product c " +
            "where c.category = :category")
    List<Product> findAllProductByCategory(@Param("category") String category);
}
