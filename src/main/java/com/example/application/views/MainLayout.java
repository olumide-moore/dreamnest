package com.example.application.views;

import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;

@Theme(themeFolder = "styles")
public class MainLayout extends AppLayout {
    public MainLayout(){
//        System.out.println(3);
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo=new H1("Dreamnest");
        logo.addClassNames("text-l", "m-m");//vaadin utility classes

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
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
}
