package com.croix.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.croix.shop.exception.ResourceNotFoundException;
import com.croix.shop.model.ItemRequest;
import com.croix.shop.model.Orderitem;
import com.croix.shop.model.Products;
import com.croix.shop.service.CatalogService;
import com.croix.shop.service.OrderItemService;
import com.croix.shop.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shop/item")
@Validated
public class OrderItemController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/add")
    public ResponseEntity<Orderitem> addOrderItem(@Valid @RequestBody ItemRequest itemRequest) {
        orderService.getOrderById(itemRequest.getId_order())
            .orElseThrow(() -> new ResourceNotFoundException("Orden con ID "+ itemRequest.getId_product() +" no se encuentra"));
        
        Orderitem orderItem = new Orderitem();
        List<Orderitem> orderItemList = orderItemService.getOrderItemById_OrderAndId_Product(itemRequest.getId_order(), itemRequest.getId_product());
        
        if (orderItemList.size() > 0) {
            orderItem = orderItemList.getFirst();
            orderItem.setAmount(orderItem.getAmount() + 1);
        }else{
            Products product = catalogService.getProductById(itemRequest.getId_product())
                .orElseThrow(() -> new ResourceNotFoundException("Producto con ID "+ itemRequest.getId_product() +" no se encuentra"));
            orderItem.setId_order(itemRequest.getId_order());
            orderItem.setId_product(itemRequest.getId_product());
            orderItem.setAmount(1L);
            orderItem.setSku(product.getSku());
        }

        orderItemService.saveOrderItem(orderItem);

        return new ResponseEntity<>(orderItem, HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<Orderitem> deletederItem(@Valid @RequestBody ItemRequest itemRequest) {
        orderService.getOrderById(itemRequest.getId_order())
            .orElseThrow(() -> new ResourceNotFoundException("Orden con ID "+ itemRequest.getId_product() +" no se encuentra"));
        
        Orderitem orderItem = new Orderitem();
        List<Orderitem> orderItemList = orderItemService.getOrderItemById_OrderAndId_Product(itemRequest.getId_order(), itemRequest.getId_product());
        
        if (orderItemList.size() > 0) {
            orderItem = orderItemList.getFirst();
           
            if(orderItem.getAmount() <= 1){
                orderItemService.deleteOrderItemById(orderItem.getId_orderitem());
            }else{
                orderItem.setAmount(orderItem.getAmount() - 1);
                orderItemService.saveOrderItem(orderItem);
            }
        }

        return new ResponseEntity<>(orderItem, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<Orderitem>> getOrderItemByIdOrder(@PathVariable Long id) {
        orderService.getOrderById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Orden con ID "+ id +" no se encuentra"));
        List<Orderitem> oderItems = orderItemService.gOrderitemById_orden(id);
        return ResponseEntity.ok(oderItems);
    }

}
