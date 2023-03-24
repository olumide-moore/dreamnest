package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
public class Product extends AbstractEntity {

    @NotEmpty
    private String category = "";

    @NotNull
    private boolean featured = false;

    @NotEmpty
    private String name = "";

    private String description="";

    @PositiveOrZero
    private float price = 0;

    @PositiveOrZero
    private Integer stock = 0;


    private String imagePath = "";

//    public Product(Product productById) {
//        super();
//    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
    public Product(){

    }



    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", imagePath='" + imagePath + '\'' +
                ", featured='" + featured + '\'' +
                '}';
    }
}
