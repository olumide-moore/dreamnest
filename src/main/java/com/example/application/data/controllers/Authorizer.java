package com.example.application.data.controllers;

import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;

import javax.servlet.http.HttpSession;


public class Authorizer{
    public static boolean isUserLoggedIn(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user!=null && user.getRole().equals(Role.USER)){
            return true;
        }
        return false;
    }
    public static String verifyUser(HttpSession session){
        return verifyAccess(session, Role.USER);
    }

    public static String verifyEmployee(HttpSession session){
        return verifyAccess(session, Role.EMPLOYEE);
    }

    public static String verifyAdmin(HttpSession session){
        return verifyAccess(session, Role.ADMIN);
    }

    private static String verifyAccess(HttpSession session, Role role){
        User user = (User) session.getAttribute("user");
        if (user == null ) {
            return  "redirect:/login";
        }else if(!(user.getRole().equals(role))){
            return "redirect:/";
        }else{
            return "";
        }
    }

    public static String verifyStaff(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null ) {
            return  "redirect:/login";
        }else if(!(user.getRole().equals(Role.EMPLOYEE) || user.getRole().equals(Role.ADMIN))){
            return "redirect:/";
        }
            return "";
    }

    public static String verifyNotStaff(HttpSession session){
        User user = (User) session.getAttribute("user");
        //If no user is logged in or logged user is a normal user
        if (user==null || user.getRole().equals(Role.USER)){
            return  "";
        }
        return "redirect:/";
    }
}