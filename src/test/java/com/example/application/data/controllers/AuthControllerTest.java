package com.example.application.data.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpSession;

import com.example.application.data.controllers.AuthController;
import com.example.application.data.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import static org.junit.jupiter.api.Assertions.*;


public class AuthControllerTest {

    private UserService userService;
    private AuthController authController;
    private HttpSession session;
    private Model model;

    @Before
    public void setUp() {
        // Create a mock for the UserService,HttSession, and Model
        userService = mock(UserService.class);
        authController = new AuthController(userService);
        session = mock(HttpSession.class);
        model = mock(Model.class);
    }

    // Test the authenticate method
    @Test
    public void testAuthenticate() {
        String email = "test@test.com";
        String password = "password";
        User user = new User(email, password, Role.USER);
        // Mock the behavior of the UserService
        when(userService.verifyUser(email, password)).thenReturn(true);
        when(userService.getUserByEmail(email)).thenReturn(user);

        String result = authController.authenticate(email, password, model, session);
        assertEquals("redirect:/", result);
    }

    // Test the authenticate method with invalid credentials
    @Test
    public void testAuthenticateInvalidCredentials() {
        String email = "test@test.com";
        String password = "wrong-password";
        // Mock the behavior of the UserService to simulate invalid login credentials
        when(userService.verifyUser(email, password)).thenReturn(false);

        String result = authController.authenticate(email, password, model, session);
        assertEquals("login", result);
    }

    //Integration test for register and login
    @Test
    public void testRegisterAndLogin() {
        String email = "test@test.com";
        String password = "password";
        String firstName = "Bob";
        String lastName = "White";
        when(userService.verifyUser(email, password)).thenReturn(true);
        // register a new user
        String registerResult = authController.register(email, password, firstName, lastName, model, session);
        assertEquals("redirect:/", registerResult);
        // login with the same credentials
        String loginResult = authController.authenticate(email, password, model, session);
        assertEquals("redirect:/", loginResult);
    }

    @Test
    public void testExistingEmailRegistration() {
        String email = "test@test.com";
        String password = "password";
        String firstName = "Bob";
        String lastName = "White";
        when(userService.verifyUser(email, password)).thenReturn(true);
        // register a new user with the same email twice
        String registerResult1 = authController.register(email, password, firstName, lastName, model, session);
        assertEquals("redirect:/", registerResult1);
        when(userService.emailExists(email)).thenReturn(true);
        String registerResult2 = authController.register(email, password, firstName, lastName, model, session);
        assertEquals("signup", registerResult2);
    }
}


