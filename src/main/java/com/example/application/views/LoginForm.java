package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value="login", layout = MainLayout.class)
@PageTitle("Login")
@RouteAlias(value="", layout = MainLayout.class)
public class LoginForm extends Div {

    public LoginForm(){
        setId("login-view");
        TextField username= new TextField("Username");
        PasswordField password = new PasswordField("Password");
        add(    new H1("Welcome"),
                username,
                password,
                new Button("Login")

        );

    }
}
