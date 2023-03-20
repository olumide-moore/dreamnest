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
import org.thymeleaf.engine.AttributeName;

// import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
    public String products(Model model) {
        model.addAttribute("category", "ALL PRODUCTS");
        model.addAttribute("products",productservice.findAllProduct());
        model.addAttribute("filter", "Sort by:");
        return "products";
    }

    @PostMapping("/category")
    public String selectCategory(@RequestParam("category") String category, Model model) {
        List<Product> products =productservice.findAllProductByCategory(category);
        model.addAttribute("category",category.toUpperCase());
        model.addAttribute("products",products);
        model.addAttribute("filter", "Sort by:");
        return "products";
    }

<<<<<<< HEAD
    // Product controller to update front-end from database
    //@RequestMapping("/search")
     //public String productSearch(@RequestParam("name") String name, Model model) {
    // List<Product> products =productservice.findAllProductByCategory(name);
     //model.addAttribute( "name", name.toUpperCase());
    // model.addAttribute("products", products)// return "products";

    @PostMapping("/pricelowtohigh")
    public String selectPrice(Model model) {
       List<Product> products =productservice.sortPriceLowToHigh();
        model.addAttribute("category", "ALL PRODUCTS");
        model.addAttribute("products",products);
        model.addAttribute("filter", "Sort by: Price (low to high)");
        return "products";
    }

    @PostMapping("/pricehightolow")
    public String selectPrice2(Model model) {
       List<Product> products =productservice.sortPriceHighToLow();
        model.addAttribute("category", "ALL PRODUCTS");
        model.addAttribute("products",products);
        model.addAttribute("filter", "Sort by: Price (high to low)");
        return "products";
    }

    @PostMapping("/nameAtoZ")
    public String selectNameOrder(Model model) {
       List<Product> products =productservice.sortNameAtoZ();
        model.addAttribute("category", "ALL PRODUCTS");
        model.addAttribute("products",products);
        model.addAttribute("filter", "Sort by: Name (A - Z)");
        return "products";
    }

    @PostMapping("/nameZtoA")
    public String selectNameOrder2(Model model) {
       List<Product> products =productservice.sortNameZtoA();
        model.addAttribute("category", "ALL PRODUCTS");
        model.addAttribute("products",products);
        model.addAttribute("filter", "Sort by: Name (Z - A)");
        return "products";
    }
    


=======
>>>>>>> f07a1dc5a46ac1f3b4515fd536ad0eb8769b0183
    @GetMapping("/edit-products")
    public String editProducts(HttpSession session, Model model) {
        // String page= Authorizer.verifyStaff(session);
        // if(page==""){
            model.addAttribute("products",productservice.findAllProduct());
                return "edit-products";
            // }
            // return page;
        }

    @PostMapping("/update-products")
    public String updateProducts(Long id, @RequestParam("name") String name, @RequestParam("category") String category, @RequestParam("price") float price, @RequestParam("stock") int stock, @RequestParam("description") String description, @RequestParam("imagePath") MultipartFile imagePath) {
        Product product;
        if (id == null) {
            product = new Product();
        } else {
            product = productservice.findProductById(id);
        }
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setStock(stock);
        product.setDescription(description);

        if (!imagePath.isEmpty()){
            try {

                // File file = new File("src/main/resources/static/images/" + imagePath.getOriginalFilename());
                // imagePath.transferTo(file);
                // String fileName = imagePath.getOriginalFilename();
                // String extension = fileName.substring(fileName.lastIndexOf("."));
                // // Generate a unique file name
                // String newFileName = UUID.randomUUID().toString() + extension;

                // // Get the bytes of the file
                // byte[] bytes = fileName.getBytes();

                // // Create a new file in the resources folder
                // Path path = Paths.get("src/main/resources/static/images/" + newFileName);
                // Files.write(path, bytes);

                byte[] bytes = imagePath.getBytes();
                Path path = Paths.get("src/main/resources/static/images/" + imagePath.getOriginalFilename());
                Files.write(path, bytes);


                // Set the imagePath property of the product object
                product.setImagePath("/images/" + imagePath.getOriginalFilename());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        //        product.setImagePath(imagePath);
        productservice.saveProduct(product);
        return "redirect:/edit-products";
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam("id") Long id) {
        productservice.deleteProduct(productservice.findProductById(id));
        return "redirect:/edit-products";
    }

    @PostMapping("/select-item")
    public String select_item(Model model, @RequestParam("productId") Long productId){
        Product product = productservice.findProductById(productId);
        model.addAttribute("product", product);
        return "select-item";
    }




}