package com.example.application.data.service;

import com.example.application.data.entity.Orders;
import com.example.application.data.repository.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    public void updateOrderStatus(Long id, String status) {
        ordersRepository.updateStatusById(id, status);
    }
    public Orders getById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }
    public void save(Orders order) {
        if (order == null){
            System.err.println("Product is null");
            return;
        }
        ordersRepository.save(order);
    }
}
