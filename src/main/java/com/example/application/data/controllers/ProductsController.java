package com.example.application.data.controllers;

import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;
import com.example.application.data.entity.Product;
import com.example.application.data.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ProductsController {

    // inject via application.properties
//    @Value("${welcome.message}")
//    private String message;

    // @Autowired
    @Autowired
    private ProductService productservice;
    




    @RequestMapping("/products")
    public String products(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return  "login";
//        }else {
            model.addAttribute("products",productservice.findAllProduct());
            return "products";
//        }
    }

    @GetMapping("/edit-products")
    public String editProducts(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("user");
//        if(user!=null) {
//            model.addAttribute("user", user);
//            if (user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.EMPLOYEE))
                model.addAttribute("products",productservice.findAllProduct());
                return "edit-products";
//        }
//        return "redirect:/";
    }

    @PostMapping("/update-products")
    public String updateProducts(Long id, @RequestParam("name") String name, @RequestParam("category") String category, @RequestParam("price") float price, @RequestParam("stock") int stock, @RequestParam("description") String description, @RequestParam("imagePath") MultipartFile imagePath) {
        Product product;
        if (id == null) {// new product
            product = new Product();
        } else { // existing product
            product = productservice.findProductById(id);
        }
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setStock(stock);
        product.setDescription(description);

        if (!imagePath.isEmpty()){
            try {
//                int i=2;

//                File file = new File("src/main/resources/static/images/" + imagePath.getOriginalFilename());
//                imagePath.transferTo(file);
                 String fileName = imagePath.getOriginalFilename();
//                 String extension = fileName.substring(fileName.lastIndexOf("."));
//                 // Generate a unique file name
//                 String newFileName = UUID.randomUUID().toString() + extension;
//
//                 // Get the bytes of the file
                 byte[] bytes = imagePath.getBytes();

                 // Create a new file in the resources folder
                 Path path = Paths.get("src/main/resources/static/images/" + fileName);
                 Files.write(path, bytes);
//
//               // Set the imagePath property of the product object
                 product.setImagePath("/images/" + fileName);
//                product.setImagePath("/images/" + fileName + "?v=" + System.currentTimeMillis());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        productservice.saveProduct(product);
        return "redirect:/edit-products";
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam("id") Long id) {
        productservice.deleteProduct(productservice.findProductById(id));
        return "redirect:/edit-products";
    }



}