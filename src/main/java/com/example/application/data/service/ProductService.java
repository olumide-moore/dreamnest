package com.example.application.data.service;

import com.example.application.data.entity.Product;
import com.example.application.data.repository.ProductRepository;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;

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
            Notification.show("Product is null");
//            System.err.println("Product is null");
            return;
        }
        productRepository.save(product);
    }

}