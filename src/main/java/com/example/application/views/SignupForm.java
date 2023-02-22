package com.example.application.views;

import com.example.application.data.entity.User;
import com.example.application.data.entity.Role;
import com.example.application.data.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

@Route("signup")
public class SignupForm extends FormLayout {
    private TextField firstName;
    private TextField lastName;
    private EmailField email;
    private PasswordField passwordHash;
    private User user;
    private UserService service;
    Binder<User> binder = new BeanValidationBinder<>(User.class);
    public SignupForm(UserService service){
        this.service= service;
        firstName= new TextField("First name");
        lastName= new TextField("Last name");
        email= new EmailField("Email");
        passwordHash = new PasswordField("Password");
        binder.bindInstanceFields(this);

    add( new H1("Sign up"),
            firstName,
            lastName,
            email,
            passwordHash,
            new Button("Register", event -> validateAndSave())
            );
    setWidth("25em");}

    private void validateAndSave() {
        try{
            user=new User(email.getValue(),passwordHash.getValue(), Role.USER);
            user.setFirstName(firstName.getValue());
            user.setLastName(lastName.getValue());
            binder.writeBean(user);
            user.hashPassword();
            service.saveUser(user);
            System.out.print("Fine");
        }catch (ValidationException e){
            Notification.show("Fill boxes");
        }
    }


    // Events

}
