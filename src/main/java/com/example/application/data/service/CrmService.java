package com.example.application.data.service;

import com.example.application.data.entity.Product;
import com.example.application.data.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final ProductRepository productRepository;

    public CrmService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProduct(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return productRepository.findAll();
        } else {
            return productRepository.search(stringFilter);
        }
    }

    public long countProduct() {
        return productRepository.count();
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public void saveProduct(Product product) {
        if (product == null) {
            System.err.println("Product is null. Are you sure you have connected your form to the application?");
            return;
        }
        productRepository.save(product);
    }

}