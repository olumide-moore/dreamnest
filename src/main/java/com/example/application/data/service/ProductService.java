package com.example.application.data.service;

import com.example.application.data.entity.Product;
import com.example.application.data.repository.ProductRepository;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAllProduct(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return productRepository.findAll();
        } else {
            return productRepository.search(stringFilter);
        }
    }
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public long countProduct() {
        return productRepository.count();
    }
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
    public void saveProduct(Product product) {
        if (product == null) {
//            Notification.show("Product is null");
            System.err.println("Product is null");
            return;
        }
        productRepository.save(product);
    }

    public List<Product> findAllProductByCategory(String category){
        return productRepository.findAllProductByCategory(category);
    }
    
    public List<Product> sortPriceLowToHigh() {
        List<Product> products = productRepository.findAll();
        products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        return products;
    }

    public List<Product> sortPriceHighToLow() {
        List<Product> products = productRepository.findAll();
        products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        return products;
    }

    public List<Product> sortNameAtoZ() {
        List<Product> products = productRepository.findAll();
        products.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        return products;
    }

    public List<Product> sortNameZtoA() {
        List<Product> products = productRepository.findAll();
        products.sort((p1, p2) -> p2.getName().compareToIgnoreCase(p1.getName()));
        return products;
    }
}