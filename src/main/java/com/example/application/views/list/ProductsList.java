package com.example.application.views.list;

import com.example.application.data.entity.Product;
import com.example.application.data.service.ProductService;
import com.example.application.data.service.ProductService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.util.Collections;

//@PWA(name = "Flow CRM Tutorial", shortName = "Flow CRM Tutorial", enableInstallPrompt = false)

@PageTitle("Products")
//@Route(value = "edit-products", layout = MainLayout.class)  //ListView still matches the empty path, but now uses MainLayout as its parent.
public class ProductsList extends VerticalLayout {
    Grid<Product> grid = new Grid<>(Product.class);
    TextField filterText = new TextField();
    ProductForm form;
    ProductService service;

    public ProductsList(ProductService service) {
        this.service=service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setProduct(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllProduct(filterText.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addProductButton = new Button("Add product");
        addProductButton.addClickListener(e -> addProduct());
        HorizontalLayout toolbar =new HorizontalLayout(filterText,addProductButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addProduct() {
        grid.asSingleSelect().clear(); //when creating a new product, we don't want an existing product selected
        Product product = new Product();
        editProduct(new Product());
    }

    private void configureGrid() {
        grid.addClassName("product-grid");
        grid.setSizeFull();
        grid.setColumns("category","name","description","price","stock","imagePath");
//        grid.addColumn(product -> product.getStatus().getName()).setHeader("Status");
        grid.getColumns().forEach(col -> col.setAutoWidth((true)));
        grid.asSingleSelect().addValueChangeListener(e -> editProduct(e.getValue()));

    }
    private void editProduct(Product product) {
        if (product==null){
            closeEditor();
        }else {
            form.setProduct(product);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
//        form = new ProductForm(service.findAllCompanies(), service.findAllStatuses());
        form=new ProductForm();
        form.setWidth("25em");

        form.addListener(ProductForm.SaveEvent.class, this::saveProduct);
        form.addListener(ProductForm.DeleteEvent.class, this::deleteProduct);
        form.addListener(ProductForm.CloseEvent.class, e -> closeEditor());
    }

    private void deleteProduct(ProductForm.DeleteEvent event) {
        service.deleteProduct(event.getProduct());
        updateList();
        closeEditor();
    }

    private void saveProduct(ProductForm.SaveEvent event) {
        service.saveProduct(event.getProduct());
        updateList();
        closeEditor();
    }

    // Remaining methods omitted
}