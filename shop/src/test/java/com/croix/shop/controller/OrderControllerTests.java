package com.croix.shop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.croix.shop.exception.ResourceNotFoundException;
import com.croix.shop.model.Orders;
import com.croix.shop.service.OrderService;

public class OrderControllerTests {

    @InjectMocks
    private OrderController controller;

    @Mock
    private OrderService orderService;

    public OrderControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        List<Orders> orders = List.of(
                new Orders.Builder().setId_order(1L).setTotal(100L).setStatus("Creado").build(),
                new Orders.Builder().setId_order(2L).setTotal(200L).setStatus("Procesado").build());

        when(orderService.getAllOrders()).thenReturn(orders);

        ResponseEntity<List<Orders>> response = controller.getAllOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @Test
    void testCreateOrder() {
        Orders inputOrder = new Orders.Builder().setId_user(1L).build();
        Orders createdOrder = new Orders.Builder()
                .setId_order(1L)
                .setId_user(1L)
                .setCreate_date(LocalDate.now())
                .setTotal(0L)
                .setStatus("Creado")
                .build();

        when(orderService.saveOrder(any())).thenReturn(createdOrder);

        ResponseEntity<Orders> response = controller.createOrder(inputOrder);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdOrder, response.getBody());
    }

    @Test
    void testGetOrderSuccess() {
        Orders order = new Orders.Builder().setId_order(1L).setTotal(100L).setStatus("Creado").build();

        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));

        ResponseEntity<Orders> response = controller.getOrder(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    void testGetOrderNotFound() {
        when(orderService.getOrderById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> controller.getOrder(1L));

        assertEquals("Orden con ID 1 no se encuentra", exception.getMessage());
    }

    @Test
    void testGetOrderByUser() {
        List<Orders> orders = List.of(
                new Orders.Builder().setId_order(1L).setId_user(1L).setTotal(100L).setStatus("Creado").build());

        when(orderService.findByIdUser(1L)).thenReturn(orders);

        ResponseEntity<List<Orders>> response = controller.getOrderByUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @Test
    void testDeleteOrderSuccess() {
        Orders order = new Orders.Builder().setId_order(1L).setTotal(100L).setStatus("Creado").build();

        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));
        doNothing().when(orderService).deleteOrderById(1L);

        ResponseEntity<Void> response = controller.deleteOrder(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderService).deleteOrderById(1L);
    }

    @Test
    void testDeleteOrderNotFound() {
        when(orderService.getOrderById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> controller.deleteOrder(1L));

        assertEquals("Orden con ID 1 no se encuentra", exception.getMessage());
    }

    @Test
    void testUpdateOrderSuccess() {
        Orders existingOrder = new Orders.Builder().setId_order(1L).setTotal(100L).setStatus("Creado").build();
        Orders updatedOrder = new Orders.Builder().setId_order(1L).setTotal(200L).setStatus("Procesado").build();

        when(orderService.getOrderById(1L)).thenReturn(Optional.of(existingOrder));
        when(orderService.saveOrder(any())).thenReturn(updatedOrder);

        ResponseEntity<Orders> response = controller.updateOrder(1L, updatedOrder);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedOrder, response.getBody());
    }

    @Test
    void testUpdateOrderNotFound() {
        Orders updatedOrder = new Orders.Builder().setId_order(1L).setTotal(200L).setStatus("Procesado").build();

        when(orderService.getOrderById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> controller.updateOrder(1L, updatedOrder));

        assertEquals("Orden con ID 1 no se encuentra", exception.getMessage());
    }
}
