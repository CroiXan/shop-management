package com.croix.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.croix.shop.model.Orderitem;
import com.croix.shop.repository.OrderItemRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Orderitem> getAllOrderItems(){
        return orderItemRepository.findAll();
    }

    public Optional<Orderitem> getOrderItemById(Long id){
        return orderItemRepository.findById(id);
    }

    public List<Orderitem> getOrderItemById_OrderAndId_Product(Long id_order, Long id_product){
        return orderItemRepository.findById_orderAndId_product(id_order,id_product);
    }

    public List<Orderitem> gOrderitemById_orden(Long id_order){
        return orderItemRepository.findById_order(id_order);
    }

    public Orderitem saveOrderItem(Orderitem orderitem){
        return orderItemRepository.save(orderitem);
    }

    public void deleteOrderItemById(Long id){
        orderItemRepository.deleteById(id);
    }
}
