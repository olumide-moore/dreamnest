package com.example.application.data.controllers;

import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;
import com.example.application.data.entity.Product;
import com.example.application.data.repository.ProductRepository;
import com.example.application.data.service.BasketService;
import com.example.application.data.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/products/";

    // inject via application.properties
//    @Value("${welcome.message}")
//    private String message;

    // @Autowired
    @Autowired
    private ProductService productservice;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketService basketService;

    @RequestMapping("/products")
    public String products(HttpSession session, Model model) {
        String page= Authorizer.verifyNotStaff(session);
        if(page==""){
            if (Authorizer.isUserLoggedIn(session)){
                User user= (User) session.getAttribute("user");
                model.addAttribute("basketCount", basketService.countProductQuantityForUser(user.getId()));
            }
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("category", "ALL PRODUCTS");
            model.addAttribute("products",productservice.findAllProduct());
            model.addAttribute("filter", "Sort by:");
            return "products";
        }
        return page;
    }

    @PostMapping("/category")
    public String selectCategory(@RequestParam("category") String category,HttpSession session, Model model) {
        String page= Authorizer.verifyNotStaff(session);
        if(page==""){
            if (Authorizer.isUserLoggedIn(session)){
                User user= (User) session.getAttribute("user");
                model.addAttribute("basketCount", basketService.countProductQuantityForUser(user.getId()));
            }
            List<Product> products =productservice.findAllProductByCategory(category);
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("category",category.toUpperCase());
            model.addAttribute("products",products);
            model.addAttribute("filter", "Sort by:");
            return "products";
        }
        return page;
    }

    @PostMapping("/pricelowtohigh")
    public String selectPrice(HttpSession session, Model model) {
        String page= Authorizer.verifyNotStaff(session);
        if(page==""){
            if (Authorizer.isUserLoggedIn(session)){
                User user= (User) session.getAttribute("user");
                model.addAttribute("basketCount", basketService.countProductQuantityForUser(user.getId()));
            }
            List<Product> products =productservice.sortPriceLowToHigh();
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("category", "ALL PRODUCTS");
            model.addAttribute("products",products);
            model.addAttribute("filter", "Sort by: Price (low to high)");
            return "products";
            }
            return page;
    }

    @PostMapping("/pricehightolow")
    public String selectPrice2(HttpSession session, Model model) {
        String page= Authorizer.verifyNotStaff(session);
        if(page==""){
            if (Authorizer.isUserLoggedIn(session)){
                User user= (User) session.getAttribute("user");
                model.addAttribute("basketCount", basketService.countProductQuantityForUser(user.getId()));
            }
            List<Product> products =productservice.sortPriceHighToLow();
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("category", "ALL PRODUCTS");
            model.addAttribute("products",products);
            model.addAttribute("filter", "Sort by: Price (high to low)");
            return "products";
        }
        return page;
    }

    @PostMapping("/nameAtoZ")
    public String selectNameOrder(HttpSession session,Model model) {
        String page= Authorizer.verifyNotStaff(session);
        if(page==""){
            if (Authorizer.isUserLoggedIn(session)){
                User user= (User) session.getAttribute("user");
                model.addAttribute("basketCount", basketService.countProductQuantityForUser(user.getId()));
            }
            List<Product> products =productservice.sortNameAtoZ();
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("category", "ALL PRODUCTS");
            model.addAttribute("products",products);
            model.addAttribute("filter", "Sort by: Name (A - Z)");
            return "products";
        }
        return page;
    }

    @PostMapping("/nameZtoA")
    public String selectNameOrder2(HttpSession session, Model model) {
        String page= Authorizer.verifyNotStaff(session);
        if(page==""){
            if (Authorizer.isUserLoggedIn(session)){
                User user= (User) session.getAttribute("user");
                model.addAttribute("basketCount", basketService.countProductQuantityForUser(user.getId()));
            }
            List<Product> products =productservice.sortNameZtoA();
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("category", "ALL PRODUCTS");
            model.addAttribute("products",products);
            model.addAttribute("filter", "Sort by: Name (Z - A)");
            return "products";
        }
        return page;
    }
    


    @GetMapping("/edit-products")
    public String editProducts(HttpSession session, Model model) {
         String page= Authorizer.verifyStaff(session);
         if(page==""){
             User user = (User) session.getAttribute("user");
             model.addAttribute("user", user);
            model.addAttribute("products",productservice.findAllProduct());
                return "edit-products";
             }
             return page;
        }

    @PostMapping("/update-products")
    public String updateProducts(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("category") String category, @RequestParam("price") float price, @RequestParam("stock") int stock, @RequestParam("description") String description, @RequestParam("imagePath") MultipartFile imagePath) throws IOException {
        String page= Authorizer.verifyStaff(session);

        if(page==""){
            Product product;
            if (id == -1) {
                product = new Product();
            } else {
                product = productservice.findProductById(id);
            }
            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            product.setStock(stock);
            product.setDescription(description);
            String imageUUID;
            User user = (User)session.getAttribute("user");
            if(user!=null && user.getRole().equals(Role.ADMIN) && !imagePath.isEmpty()){
                imageUUID = imagePath.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
                Files.write(fileNameAndPath, imagePath.getBytes());
                product.setImagePath(imageUUID);
            }

            productservice.saveProduct(product);
            redirectAttributes.addFlashAttribute("message", "Products updated successfully!");

            return "redirect:/edit-products";
        }
        return page;
    }
    @PostMapping("/change-featured")
    public String changeFeature(HttpSession session, @RequestParam("productId") Long productId, @RequestParam("isChecked") boolean isChecked ){
        String page= Authorizer.verifyStaff(session);
        if(page==""){
            Product product=productservice.findProductById(productId);
            product.setFeatured(isChecked);
            productservice.saveProduct(product);
            return "redirect:/edit-products";
        }
        return page;
    }

    @PostMapping("/delete-products")
    public String deleteProduct(HttpSession session, Long deleteid) throws IOException {
        String page= Authorizer.verifyStaff(session);
        if(page==""){
            String filepath = productservice.findProductById(deleteid).getImagePath();
            File myObj = new File(uploadDirectory + filepath);
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
            productservice.deleteProduct(productservice.findProductById(deleteid));
            return "redirect:/edit-products";
        }
        return page;
    }

    @PostMapping("/select-item")
    public String select_item(HttpSession session,Model model, Long productId){
        String page= Authorizer.verifyNotStaff(session);
        if(page=="") {
            if (Authorizer.isUserLoggedIn(session)){
                User user= (User) session.getAttribute("user");
                model.addAttribute("basketCount", basketService.countProductQuantityForUser(user.getId()));
            }
            Product product = productservice.findProductById(productId);
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("product", product);
            return "select-item";
        }
        return page;

    }
}