package com.example.application.data.service;

import com.example.application.data.entity.Basket;
import com.example.application.data.entity.Product;
import com.example.application.data.repository.BasketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

@Service
public class BasketService {

    private final BasketRepository basketrepository;

    public BasketService(BasketRepository basketrepository) {
        this.basketrepository = basketrepository;
    }


    public long countProductForUser(Long userId){ return basketrepository.countByUser_id(userId);
    }
    public long countProductQuantityForUser(Long userId){ return basketrepository.sumQuantityByUser_id(userId);
    }
    public List<Basket> findAllBasket() {
        return basketrepository.findAll();
    }
    public Basket findBasketById(Long id) {
        return basketrepository.findById(id).orElse(null);
    }

    public List<Basket> findAllBasketByUserId(Long id){
        return basketrepository.findAllByUserId(id);
    }
    //find all basket by user id
//    public List<Basket> findAllBasketByUserId(Long id) {
//        return basketrepository.(id);
//    }

    public Basket findByUserIdAndProductId(Long userId, Long productId){
        return basketrepository.findByUserIdAndProductId(userId,productId);
    }

    public void updateBasket(Long userId, Product product, Integer quantity) {
        Basket basket = basketrepository.findByUserIdAndProductId(userId, product.getId());
        if (basket == null) {
            basket = new Basket();
            basket.setUser_id(userId);
            basket.setProduct(product);
        }
            basket.setQuantity(quantity);
        if (basket.getQuantity() == 0) {
            basketrepository.delete(basket);
            return;
        }
        basketrepository.save(basket);
    }

    public void deleteAllBasketByUserId(Long userId) {
        basketrepository.deleteByUser_id(userId);
    }
}