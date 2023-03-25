package com.example.application.data.controllers;

import com.example.application.data.entity.*;
import com.example.application.data.service.BasketService;
import com.example.application.data.service.OrdersService;
import com.example.application.data.service.ProductService;
import com.example.application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class OrdersController
{
    @Autowired
    BasketService basketService;
    @Autowired
    OrdersService ordersService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("/orders")
    public String orders(HttpSession session, Model model){
        if (Authorizer.verifyUser(session)==""){
            User user = (User) session.getAttribute("user");
            HashMap<Long,Product> products= new HashMap<>(); // <productId,Product>
            List<Orders> ordersList = ordersService.findByUser_id(user.getId());
            for (Orders orders: ordersList){
                for (OrderItem orderItem: orders.getOrderItems()){
                    Long productId = orderItem.getProduct_id();
                    products.put(productId, productService.findProductById(productId));
                }
            }
            model.addAttribute("basketCount", basketService.countProductForUser(user.getId()));
            model.addAttribute("user", user);
            model.addAttribute("orders",ordersList);
            model.addAttribute("products",products);
            return "orders";
        }
        return "redirect:/login";

    }

    @GetMapping("/edit-orders")
    public String editOrders(HttpSession session, Model model){
         String page= Authorizer.verifyStaff(session);
         if(page==""){
        HashMap<Long,Product> products= new HashMap<>(); // <productId,Product>
        HashMap<Long,User> users= new HashMap<>(); // <userId,User>
            List<Orders> ordersList = ordersService.findAllOrders();
            for (Orders orders: ordersList){
                for (OrderItem orderItem: orders.getOrderItems()){
                    Long productId = orderItem.getProduct_id();
                    products.put(productId, productService.findProductById(productId));
                }
                Long userId=orders.getUser_id();
                users.put(userId,userService.getUserById(userId));
            }
            model.addAttribute("orders",ordersList);
            model.addAttribute("products",products);
            model.addAttribute("user", session.getAttribute("user"));

            model.addAttribute("users",users);
            return "edit-orders";
         }
         return page;
    }

    @PostMapping("/update-orders")
    public String updateOrders(HttpSession session, @RequestParam("orderId") Long orderId, @RequestParam("status") String status){
         String page= Authorizer.verifyStaff(session);
         if(page==""){
            Orders orders = ordersService.getById(orderId);
            orders.setStatus(OrderStatus.valueOf(status));
            ordersService.save(orders);
            return "redirect:/edit-orders";
         }
         return page;
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model, RedirectAttributes redirectAttributes){
        User user = (User) session.getAttribute("user");
        if (user == null ) {
            return  "redirect:login";
        }else if(!(user.getRole().equals(Role.USER))){
            return "redirect:/";
        }else{
            Long userId=user.getId();
            List<Basket> baskets = basketService.findAllBasketByUserId(userId);
            if (!baskets.isEmpty()){
                //Check for stock
                for (Basket basket : baskets) {
                    Product product = basket.getProduct();
                    if (!enoughQuantity(product, basket.getQuantity())) {
                        redirectAttributes.addFlashAttribute("error", "Not enough quantity in stock for " + product.getName()+". Only "+product.getStock()+" left in stock.");
                        return "redirect:/basket";
                    }
                }
                Orders orders = new Orders();
                orders.setUser_id(userId);
                orders.setStatus(OrderStatus.Received);
                orders.setDate(new Date());
                float total = 0;
                List<OrderItem> orderItems = new ArrayList<>(); // create orderItems list
                for (Basket basket : baskets) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct_id(basket.getProduct().getId());
                    orderItem.setCurrent_price(basket.getProduct().getPrice());
                    orderItem.setQuantity(basket.getQuantity());
                    orderItem.setOrders(orders);
                    orderItems.add(orderItem); // add orderItem to orderItems kust
                    // update stock
                    Product product = basket.getProduct();
                    int stock = product.getStock()-basket.getQuantity();
                    if (stock<0){
                        stock=0;
                    }
                    product.setStock(stock);
                    productService.saveProduct(product);
                    total += basket.getProduct().getPrice() * basket.getQuantity();
                }
                orders.setTotal(total);
                orders.setOrderItems(orderItems); // add orderItems to orders
                ordersService.save(orders);
                basketService.deleteAllBasketByUserId(userId);  // delete all basket items
                redirectAttributes.addFlashAttribute("message", "Your order has been placed!");
                return ("redirect:/orders");
            } else {

                model.addAttribute("user", session.getAttribute("user"));
                model.addAttribute("message", "Your basket is empty");
                return "basket";
            }
        }
    }
    private boolean enoughQuantity(Product product, Integer quantity){
        if (product.getStock() >= quantity){
            return true;
        }
        return false;
    }
}


