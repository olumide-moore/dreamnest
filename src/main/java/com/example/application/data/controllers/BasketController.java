package com.example.application.data.controllers;

import com.example.application.data.entity.Basket;
import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;
import com.example.application.data.entity.Product;
import com.example.application.data.service.BasketService;

import com.example.application.data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;


@Controller
public class BasketController {
    @Autowired
    private BasketService basketService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/basket")
    public String basket(HttpSession session, Model model) {
       User user = (User) session.getAttribute("user");
       if (user == null ) {
           return  "redirect:login";
       }else if(!(user.getRole().equals(Role.USER))){
              return "redirect:/";
       }else{
           HashMap<Long,Product> products= new HashMap<>(); // <productId,Product>
           List<Basket> basketList = basketService.findAllBasketByUserId(user.getId());
           float total = 0;
           for (Basket basket : basketList) {
               Long productId = basket.getProduct().getId();
               products.put(productId, productService.findProductById(productId));
                total += products.get(productId).getPrice() * basket.getQuantity();
           }
           model.addAttribute("basket",basketList);
           model.addAttribute("products",products);
              model.addAttribute("total",total);
           return "basket";
            }
       }
    public String verifyUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null ) {
            return  "redirect:/login";
        }else if(!(user.getRole().equals(Role.USER))){
            return "redirect:/";
        }else{
            return "";
        }
    }

    @PostMapping("/basket/update")
    public String updateBasket(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        if (verifyUser(session)=="") {
            User user = (User) session.getAttribute("user");
            Product product=productService.findProductById(productId);
//            if (!enoughQuantity(product,quantity)){
//                redirectAttributes.addFlashAttribute("error", "Sorry, not enough stock to add more quantity");
//                return "redirect:/basket";
//            }
            Basket basket = basketService.findByUserIdAndProductId(user.getId(), productId);
            basketService.updateBasket(user.getId(), productService.findProductById(productId), quantity);
        }
            return "redirect:/basket";
    }

    @PostMapping("/basket/add")
    public String addBasket(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
       if (verifyUser(session)==""){
            User user = (User) session.getAttribute("user");
            Product product=productService.findProductById(productId);
//            if (!enoughQuantity(product,quantity)){
//                redirectAttributes.addFlashAttribute("error", "Sorry, not enough stock to add more quantity");
//                return "redirect:/basket";
//            }
            Basket basket = basketService.findByUserIdAndProductId(user.getId(), productId);
            if (basket!=null){
                quantity = basket.getQuantity()+quantity;
            }
            basketService.updateBasket(user.getId(), productService.findProductById(productId), quantity);
       }
        return "redirect:/basket";
    }

    private boolean enoughQuantity(Product product, Integer quantity){
        if (product.getStock() >= quantity){
            return true;
        }
            return false;
    }



}