package com.croix.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.croix.shop.model.Orders;
import com.croix.shop.repository.OrderRepository;

public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    public OrderServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        Orders order = new Orders.Builder()
                        .setId_order(1L)
                        .setCreate_date(LocalDate.now())
                        .setId_user(1L)
                        .setTotal(100L)
                        .setStatus("Created")
                        .build();

        List<Orders> orders = List.of(order);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Orders> result = orderService.getAllOrders();

        assertEquals(orders, result);
        verify(orderRepository).findAll();
    }

    @Test
    void testGetOrderByIdFound() {
        Orders order = new Orders.Builder()
                        .setId_order(1L)
                        .setCreate_date(LocalDate.now())
                        .setId_user(1L)
                        .setTotal(100L)
                        .setStatus("Created")
                        .build();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Orders> result = orderService.getOrderById(1L);

        assertTrue(result.isPresent());
        assertEquals(order, result.get());
    }

    @Test
    void testSaveOrder() {
        Orders order = new Orders.Builder()
                        .setId_order(1L)
                        .setCreate_date(LocalDate.now())
                        .setId_user(1L)
                        .setTotal(100L)
                        .setStatus("Created")
                        .build();

        when(orderRepository.save(order)).thenReturn(order);

        Orders result = orderService.saveOrder(order);

        assertEquals(order, result);
        verify(orderRepository).save(order);
    }
}
