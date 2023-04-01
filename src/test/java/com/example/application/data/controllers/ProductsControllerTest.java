package com.example.application.data.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.example.application.data.controllers.AuthController;
import com.example.application.data.controllers.ProductsController;
import com.example.application.data.entity.Product;
import com.example.application.data.entity.Role;
import com.example.application.data.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import static org.junit.jupiter.api.Assertions.*;


public class ProductsControllerTest {
    private UserService userService;
    private ProductService productService;
    private AuthController authController;
    private ProductsController productsController;
    private HttpSession session;
    private Model model;
    private RedirectAttributes redirectAttributes;

    @Before
    public void setUp() {
        // Create a mock for the UserService,HttSession, and Model
        userService = mock(UserService.class);
        productService=mock(ProductService.class);
        authController = new AuthController(userService);
        productsController=new ProductsController(productService);
        session = mock(HttpSession.class);
        model = mock(Model.class);
    }

    @Test
    public void testUpdateProducts() {
        String email = "admin@admin.com";
        String password = "password";
        User user = new User(email, password, Role.ADMIN);
        // Mock the behavior of the UserService
        when(userService.verifyUser(email, password)).thenReturn(true);
        when(userService.getUserByEmail(email)).thenReturn(user);

        String result = authController.authenticate(email, password, model, session);
        assertEquals("redirect:/", result);
        try{
            Product product = new Product();
            product.setName("name");
            product.setCategory("category");
            product.setPrice(10);
            product.setStock(1);
            productsController.updateProducts(redirectAttributes, session, product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getStock(), product.getDescription(), null);
            when(productService.findProductById(product.getId())).thenReturn(product);
            Product foundproduct =productService.findProductById(product.getId());
            assertNotNull(foundproduct);
            assertEquals(foundproduct.getName(), product.getName());
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }
    
}
