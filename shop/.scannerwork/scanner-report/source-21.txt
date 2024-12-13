package com.croix.shop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.croix.shop.exception.ResourceNotFoundException;
import com.croix.shop.model.ItemRequest;
import com.croix.shop.model.Orderitem;
import com.croix.shop.model.Orders;
import com.croix.shop.model.Products;
import com.croix.shop.service.CatalogService;
import com.croix.shop.service.OrderItemService;
import com.croix.shop.service.OrderService;

public class OrderItemControllerTests {

    @InjectMocks
    private OrderItemController controller;

    @Mock
    private OrderService orderService;

    @Mock
    private CatalogService catalogService;

    @Mock
    private OrderItemService orderItemService;

    public OrderItemControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrderItemSuccess() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId_order(1L);
        itemRequest.setId_product(1L);
        Orders order = new Orders.Builder().setId_order(1L).setTotal(100L).build();
        Products product = new Products();
        product.setId_product(1L);
        product.setSku("SKU123");
        product.setPrice(50L);
        product.setStock(10);
        Orderitem expectedOrderItem = new Orderitem.Builder()
                                        .setId_order(1L)
                                        .setId_product(1L)
                                        .setId_orderitem(1L)
                                        .setSku("SKU123").build();

        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));
        when(catalogService.getProductById(1L)).thenReturn(Optional.of(product));
        when(orderItemService.getOrderItemById_OrderAndId_Product(1L, 1L)).thenReturn(Collections.emptyList());
        when(orderItemService.saveOrderItem(any())).thenReturn(expectedOrderItem);
        when(orderService.saveOrder(any())).thenReturn(order);

        ResponseEntity<Orderitem> response = controller.addOrderItem(itemRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("SKU123", response.getBody().getSku());
        verify(orderItemService).saveOrderItem(any());
    }

    @Test
    void testDeletederItemNotFound() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId_order(2L);
        itemRequest.setId_product(2L);

        when(orderService.getOrderById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> controller.deletederItem(itemRequest));

        assertEquals("Orden con ID 2 no se encuentra", exception.getMessage());
    }

    @Test
    void testDeletederItemSuccessDecreaseAmount() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId_order(1L);
        itemRequest.setId_product(1L);
        Orders order = new Orders.Builder().setId_order(1L).setTotal(100L).build();
        Products product = new Products();
        product.setPrice(50L);
        Orderitem existingOrderItem = new Orderitem.Builder()
                                    .setId_order(1L)
                                    .setId_product(1L)
                                    .setId_orderitem(1L)
                                    .setAmount(2L)
                                    .build();

        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));
        when(catalogService.getProductById(1L)).thenReturn(Optional.of(product));
        when(orderItemService.getOrderItemById_OrderAndId_Product(1L, 1L)).thenReturn(List.of(existingOrderItem));

        ResponseEntity<Orderitem> response = controller.deletederItem(itemRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderItemService).saveOrderItem(any());
    }

    @Test
    void testDeletederItemSuccessDeleteItem() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId_order(1L);
        itemRequest.setId_product(1L);
        Orders order = new Orders.Builder().setId_order(1L).setTotal(100L).build();
        Products product = new Products();
        product.setPrice(50L);
        Orderitem existingOrderItem = new Orderitem.Builder()
                                    .setId_order(1L)
                                    .setId_product(1L)
                                    .setId_orderitem(1L)
                                    .setAmount(1L)
                                    .build();

        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));
        when(catalogService.getProductById(1L)).thenReturn(Optional.of(product));
        when(orderItemService.getOrderItemById_OrderAndId_Product(1L, 1L)).thenReturn(List.of(existingOrderItem));
        doNothing().when(orderItemService).deleteOrderItemById(1L);

        ResponseEntity<Orderitem> response = controller.deletederItem(itemRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderItemService).deleteOrderItemById(1L);
    }

    @Test
    void testDeletederItemProductNotFound() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId_order(1L);
        itemRequest.setId_product(1L);
        Orders order = new Orders.Builder().setId_order(1L).setTotal(100L).build();

        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));
        when(catalogService.getProductById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> controller.deletederItem(itemRequest)
        );

        assertEquals("Producto con ID 1 no se encuentra", exception.getMessage());
    }

    @Test
    void testGetOrderItemByIdOrderSuccess() {
        Long orderId = 1L;
        Orderitem orderItem1 = new Orderitem.Builder()
                                        .setId_order(orderId)
                                        .setId_product(1L)
                                        .setId_orderitem(1L)
                                        .setSku("SKU123").build();
        Orderitem orderItem2 = new Orderitem.Builder()
                                        .setId_order(orderId)
                                        .setId_product(2L)
                                        .setId_orderitem(2L)
                                        .setSku("SKU124").build();
        List<Orderitem> orderItems = List.of(orderItem1,orderItem2);

        when(orderService.getOrderById(orderId)).thenReturn(Optional.of(new Orders.Builder().setId_order(orderId).setTotal(100L).build()));
        when(orderItemService.gOrderitemById_orden(orderId)).thenReturn(orderItems);

        ResponseEntity<List<Orderitem>> response = controller.getOrderItemByIdOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderItems, response.getBody());
    }
}
