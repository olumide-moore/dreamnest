package com.example.application.views;

import com.example.application.data.entity.User;
import com.example.application.data.entity.Role;
import com.example.application.data.service.AuthService;
import com.example.application.views.list.ProductsList;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;

@Theme(themeFolder = "styles")
//@CssImport(value = "./themes/styles/styles.css", themeFor="vaadin-menu-bar")
public class MainLayout extends AppLayout {
    private AuthService authService;
    private VerticalLayout drawer_layout;

    public MainLayout(AuthService authService){
        this.authService = authService;
        createHeader();
//        remove Drawer
// Remove the DrawerToggle component by ID
        getElement().getChild(0).removeChild(getElement().getChild(0).getChild(0));

//// Remove the Drawer component by ID
//        getElement().getChild(0).removeChild(getElement().getChild(0).getChild(1));
//        createDrawer();
    }

    private void createHeader() {
        H1 logo=new H1("Dreamnest");
        logo.addClassNames("text-l", "m-m");//vaadin utility classes

        //Top Menubar
        MenuBar menuBar= new MenuBar();
        menuBar.addClassName("menuBar"); //set class for menubar
        //Set menu items
//        MenuItem home= menuBar.addItem("Home");
        MenuItem products = menuBar.addItem("Products");
//        MenuItem about = menuBar.addItem("About Us");
//        MenuItem contact = menuBar.addItem("Contact");
        MenuItem user =createIconItem(menuBar,VaadinIcon.USER,"User");
        SubMenu userSubMenu= user.getSubMenu();
        userSubMenu.addItem("Sign out").addClickListener( event->
                UI.getCurrent().navigate("logout")
        );

//        createIconItem(menuBar,VaadinIcon.CART,"Basket");
//        //Set class for all menuitems
////        menuBar.getItems().forEach(item -> item.getElement().getClassList().add("navbar-buttons"));
//        home.getElement().getClassList().add("menu-buttons");
//        home.getElement().setAttribute("theme", "icon-on-top");
//        home.getElement().getClassList().add("home-menu-item");

//        menuBar.setOpenOnHover(true);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, menuBar);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
//        header.addClassNames("py-0", "px-m");
        header.addClassName("header");

        addToNavbar(header);

    }
    private void createDrawer(){

        User user= VaadinSession.getCurrent().getAttribute(User.class);
        if (user!=null) {
            authService.getAuthorizedRoutes(user.getRole()).stream()
                    .forEach(r -> createDrawerItem(r.name(), r.view()));

            addToDrawer(
                    drawer_layout);
        }

    }
    private void createDrawerItem(String text, Class<? extends Component> target) {
        drawer_layout.add(new RouterLink(text, target));
        //Removed for now as authorization needed to create routes
//        RouterLink productsList  = new RouterLink("List", ProductsList.class);
//        productsList.setHighlightCondition(HighlightConditions.sameLocation());
        //Get user stored in current session

    }

    private MenuItem createIconItem(MenuBar menu, VaadinIcon iconName,
                                    String tooltipText) {
        Icon icon = new Icon(iconName);
        MenuItem item = menu.addItem(icon);
        item.getElement().setAttribute("title",tooltipText);

        item.getElement().setAttribute("aria-label", tooltipText);

        return item;
    }
}
