package com.example.application.data.controllers;

import com.example.application.data.entity.Product;
import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;

import com.example.application.views.list.ProductsList;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.spring.scopes.VaadinUIScope;
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
public class AuthController {

    // inject via application.properties
//    @Value("${welcome.message}")
//    private String message;

    // @Autowired
    // private AuthService authService;

    @Autowired
    private UserService userService;
    
//    private final ProductsList productsList;
//
//    public AuthController(ProductsList productsList){
//        this.productsList=productsList;
//    }

//    @GetMapping("/")
//    public String welcome(Model model) {
//////        System.out.println("Message from application.properties : "+message);
//////        model.addAttribute("message", message);
////        VaadinSession session = VaadinSession.getCurrent();
////        if (session!= null && session.getAttribute(User.class)!=null) {
//////        return "redirect:/query?message=Thymeleaf+Is+Great!";
////            return  "home";
////        }
////        else {
//////            UI.getCurrent().navigate("login");
//
//            return "login"; //view
////        }
//    }

    @GetMapping("/")
    public String welcome(HttpSession session, Model model) {
        User user =null;
        if (session.getAttribute("user")!=null){
            user = (User) session.getAttribute("user");
        }
        if (user == null) {
//            return  "login";
//            return "redirect:/edit-products";
//            return "login";
            return home(model ,null);

        }else {
            return home(model,user);
        }
    }

    @PostMapping("/login") //add session
    public String authenticate(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        if (userService.verifyUser(email, password)){
            // session.setAttribute("user",email);
            User user = userService.getUserByEmail(email);
            session.setAttribute("user", user);
            return home(model, user);
        }
        else{
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
//        Notification.show("User: "+user);
    }


    @GetMapping("/home")
    public String home(Model model, User user) {
        if (user!=null)
            model.addAttribute("user", user.getFirstName());
        return "home";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }


    //sign up page
    @GetMapping("/signup")
    public String signup(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "home";
        }else {
            return "signup";
        }
    }

    @GetMapping("/contactus")
    public String contactus(){
        return "contactus";
    }
    @GetMapping("/aboutus")
    public String aboutus(){
        return "aboutus";
    }
    @GetMapping("/user")
    public String userClicked(HttpSession session){
        if (session.getAttribute("user") != null) {
            return  "logout";
        }else {
            return "login";
        }
    }
    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, Model model, HttpSession session) {
        if (userService.emailExists(email)){
            model.addAttribute("error", "An account already exists with this email");
            return "signup";
        }
        else{
            User user=new User(email,password, Role.USER);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.hashPassword();
            userService.saveUser(user);
            model.addAttribute("user", user);
            session.setAttribute("user",user);
            return "home";
        }
    }



}