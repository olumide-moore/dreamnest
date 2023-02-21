package com.example.application.views;

import com.example.application.views.list.ListView;
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
import com.vaadin.flow.theme.Theme;

@Theme(themeFolder = "css")
@CssImport(value = "./css/style.css")
public class MainLayout extends AppLayout {
    public MainLayout(){
//        System.out.println(3);
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo=new H1("Dreamnest");
        logo.addClassNames("text-l", "m-m");//vaadin utility classes



        //Top Menubar
        MenuBar menuBar= new MenuBar();
        menuBar.addClassName("menuBar");
        MenuItem home= menuBar.addItem("Home");


        MenuItem products = menuBar.addItem("Products");
        MenuItem about = menuBar.addItem("About Us");
        MenuItem contact = menuBar.addItem("Contact");
        MenuItem user =createIconItem(menuBar,VaadinIcon.USER,"User");
        SubMenu userSubMenu= user.getSubMenu();
        userSubMenu.addItem("Sign out");
        createIconItem(menuBar,VaadinIcon.CART,"Basket");

        menuBar.setOpenOnHover(true);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, menuBar);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");
        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink listView  = new RouterLink("List", ListView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(
                new VerticalLayout(
                        listView
                ));
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
