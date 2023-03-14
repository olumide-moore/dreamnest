package com.example.application.data.repository;

import com.example.application.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
    @Transactional
    @Modifying
    @Query("update Orders o set o.status = ?2 where o.id = ?1")
    void updateStatusById(Long id, String status);

}
