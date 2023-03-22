package com.example.application.data.controllers;

import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;
import com.example.application.data.service.AuthService;
import com.example.application.data.service.UserService;

import com.vaadin.flow.component.UI;
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

    @Autowired
    private UserService userService;

    public AuthController(UserService userService){
        this.userService= userService;
    }


    @GetMapping("/")
    public String welcome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if(user!=null) {
            model.addAttribute("user", user);
        }
        return "home";

    }
    @PostMapping("/authenticate") //add session
    public String authenticate(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        if (userService.verifyUser(email, password)){
            User user = userService.getUserByEmail(email);
            session.setAttribute("user", user);
            return "redirect:/";
        }
        else{
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
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
            session.setAttribute("user",user);
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model){
        if (session.getAttribute("user")!=null){
            return "redirect:/";
        }else {
            return "login";
        }
    }

    //sign up page
    @GetMapping("/signup")
    public String signup(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
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
    public String userClicked(HttpSession session, Model model){
        if (session.getAttribute("user") != null) {
            return  logout(session);
        }else {
            return "login";
        }
    }




}