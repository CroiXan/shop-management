package com.croix.shop.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.croix.shop.exception.ResourceNotFoundException;
import com.croix.shop.model.Orders;
import com.croix.shop.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shop/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> order = orderService.getAllOrders();
        return ResponseEntity.ok(order);
    }
    
    @PostMapping
    public ResponseEntity<Orders> createOrder(@Valid @RequestBody Orders order) {
        order.setCreate_date(LocalDate.now());
        Orders newOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable Long id) {
        Orders order = orderService.getOrderById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ordn con ID "+ id +" no se encuentra"));
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.getOrderById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Orden con ID "+ id +" no se encuentra"));
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @Valid @RequestBody Orders updatedOrder ) {
        Orders order = orderService.getOrderById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Orden con ID "+ id +" no se encuentra"));
        order.setStatus(updatedOrder.getStatus());
        order.setTotal(updatedOrder.getTotal());
        order.setId_user(updatedOrder.getId_user());
        Orders orderResult = orderService.saveOrder(order);
        return ResponseEntity.ok(orderResult);
    }

}
