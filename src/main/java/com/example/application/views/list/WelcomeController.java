package com.example.application.views.list;


import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    // inject via application.properties
//    @Value("${welcome.message}")
//    private String message;

    // @Autowired
    // private AuthService authService;
    @Autowired
    private UserService userService;
    


    // private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String welcome(Model model) {
////        System.out.println("Message from application.properties : "+message);
////        model.addAttribute("message", message);
//        model.addAttribute("tasks", tasks);
//        VaadinSession session = VaadinSession.getCurrent();
//        if (session!= null && session.getAttribute(User.class)!=null) {
////        return "redirect:/query?message=Thymeleaf+Is+Great!";
//            return  "html/home";
//        }
//        else {
////            UI.getCurrent().navigate("login");

            return "html/login"; //view
//        }
    }
    @PostMapping("/login")
    public String verifyUser(@RequestParam String email, @RequestParam String password, Model model) {
        if (userService.verifyUser(email, password)){
            model.addAttribute("email", email);
            return "html/home";}
        else{
            return "html/login";
        }
        
//        Notification.show("User: "+user);

        }
}