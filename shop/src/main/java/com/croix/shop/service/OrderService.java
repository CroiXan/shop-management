package com.croix.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.croix.shop.model.Orders;
import com.croix.shop.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    public Orders saveOrder(Orders order){
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }

}
