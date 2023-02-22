package com.example.application.data.service;

import com.example.application.data.entity.User;
import com.example.application.data.entity.Role;
import com.example.application.data.repository.UserRepository;
import com.example.application.views.MainLayout;
import com.example.application.views.list.ProductsList;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate(String email, String password) throws AuthException {
         User user =userRepository.getByEmail(email);
         if (user != null && user.verifyPassword(password)){
            //Store user object in session
             VaadinSession.getCurrent().setAttribute(User.class,user);
             createRoutes(user.getRole());
         } else {
             throw new AuthException();
         }

    }

    //Create routes for this session
    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream()
                .forEach(path ->
                        RouteConfiguration.forSessionScope().setRoute(path.route,path.view, MainLayout.class));

    }

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view){

    }
    //Method for getting authorized routes for user
    public List<AuthorizedRoute> getAuthorizedRoutes(Role role){
        
        List<AuthorizedRoute> authorizedRoutes = new ArrayList<>();
        if (role.equals(Role.USER)){
            authorizedRoutes.add(new AuthorizedRoute("edit-products", "Products", ProductsList.class));
        }else if (role.equals(Role.EMPLOYEE)){
            
        } else if (role.equals(Role.ADMIN)) {
            
        }
        return authorizedRoutes;
    }
    
    public class  AuthException extends Exception{

    }

}
