package com.example.application.data.repository;

import com.example.application.data.entity.Product;
import com.example.application.data.entity.User;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);


}
