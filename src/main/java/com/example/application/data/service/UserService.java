package com.example.application.data.service;

import com.example.application.data.entity.User;
import com.example.application.data.repository.UserRepository;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        
//         if (user == null) {
//             Notification.show("User is null");
// //            System.err.println("User is null");
//             return;
//         }
        userRepository.save(user);
    }

    //verify user
    public boolean verifyUser(String email, String password) {
        User user = userRepository.getByEmail(email);
        if (user != null && user.verifyPassword(password)) {
            return true;
        } else {
            return false;
        }
    }

    //check if email exists
    public boolean emailExists(String email) {
        User user = userRepository.getByEmail(email);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    //get user by email
    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    //get user by id
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }


}
