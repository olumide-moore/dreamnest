package com.example.application.data.repository;

import com.example.application.data.entity.Product;
import com.example.application.data.entity.User;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);

    @Query("select c from User c " +
            "where c.role = 1 or c.role = 2")
    List<User> getAllStaff();

}
