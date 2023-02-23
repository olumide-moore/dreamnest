package com.example.application.views;

import com.example.application.data.service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

//@Route(value="login", layout = MainLayout.class)
@PageTitle("Login")
//@RouteAlias(value="", layout = MainLayout.class)
public class LoginForm extends FormLayout {
    private EmailField email;
    private PasswordField password;
    public LoginForm( AuthService authService){
         email= new EmailField("Email");
         password = new PasswordField("Password");
         Button button = new Button("Login");
//        button.addClickListener(e -> button.getUI().ifPresent(ui -> ui.navigate("edit-products")));
        button.addClickListener(event -> {
            try {
                authService.authenticate(email.getValue(), password.getValue());
                UI.getCurrent().navigate("edit-products");
//                UI.getCurrent().getPage().setLocation("/templates/welcome.html");
            } catch (AuthService.AuthException e) {
                Notification.show("Invalid details");
            }
            });

        add(    new H1("Welcome"),
                email,
                password,
                button
        );

        setWidth("25em");

    }
}
