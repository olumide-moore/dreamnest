package com.example.application.data.controllers;

import com.example.application.data.entity.Basket;
import com.example.application.data.entity.Orders;
import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;
import com.example.application.data.service.BasketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrdersController
{

    BasketService basketService;

    @GetMapping("/orders")
    public String orders(){
        return "orders";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user == null ) {
            return  "redirect:login";
        }else if(!(user.getRole().equals(Role.USER))){
            return "redirect:/";
        }else{
            List<Basket> baskets = basketService.findAllBasketByUserId(user.getId());
            if (!baskets.isEmpty()){
                Orders orders;

            }
            return "orders";
        }
    }
}


