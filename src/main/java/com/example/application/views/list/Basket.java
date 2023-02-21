package com.example.application.views.list;

import com.example.application.data.entity.Product;
import com.example.application.data.service.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.util.Collections;

//@PWA(name = "Flow CRM Tutorial", shortName = "Flow CRM Tutorial", enableInstallPrompt = false)
@PageTitle  ("Basket | Vaadin CRM")
@Route(value = "basket", layout = MainLayout.class)  //ListView still matches the empty path, but now uses MainLayout as its parent.


public class Basket extends VerticalLayout {
    Grid<Product>  grid = new Grid<>(Product.class);
    TextField filterText = new TextField();
    ProductForm form;
    CrmService service;

    public Basket(CrmService service) {
        this.service=service;
        addClassName("list-view");
        setSizeFull();
        add(new Label("Basket"),grid);
    }

   // Remaining methods omitted
}