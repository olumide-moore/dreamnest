package com.example.application.data.controllers;

import com.example.application.data.entity.*;
import com.example.application.data.service.ProductService;
import com.example.application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
public class StaffController {

        @Autowired
        private UserService userService;

        @GetMapping("/edit-staff")
        public String editProducts(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("user");
//        if(user!=null) {
//            model.addAttribute("user", user);
//            if (user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.EMPLOYEE))
            model.addAttribute("staffs", userService.getAllStaff());
            return "edit-staff";
//        }
//        return "redirect:/";


        }
    @PostMapping("/update-staff")
    public String updateStaff(HttpSession session, @RequestParam("staffId") Long staffId, @RequestParam("role") String role){
        // String page= Authorizer.verifyStaff(session);
        // if(page==""){
        User user = userService.getUserById(staffId);
        user.setRole(user.getRole().valueOf(role));
        userService.saveUser(user);
        return "redirect:/edit-staff";
        // }
        // return page;
    }
    @PostMapping("/delete-staff")
    public String deleteStaff(Long Id) throws IOException {
        User user = userService.getUserById(Id);
        user.setRole(Role.USER);
        userService.saveUser(user);
        return "redirect:/edit-staff";
    }
}