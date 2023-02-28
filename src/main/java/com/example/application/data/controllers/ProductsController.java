package com.example.application.data.controllers;

import com.example.application.data.entity.User;
import com.example.application.data.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ProductsController {

    // inject via application.properties
//    @Value("${welcome.message}")
//    private String message;

    // @Autowired
    @Autowired
    private ProductService productservice;
    




    @RequestMapping("/products")
    public String products(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return  "login";
//        }else {
            return "products";
//        }
    }
   




}