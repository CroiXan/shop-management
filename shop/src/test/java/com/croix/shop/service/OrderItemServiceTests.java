package com.croix.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.croix.shop.model.Orderitem;
import com.croix.shop.repository.OrderItemRepository;

public class OrderItemServiceTests {

    @InjectMocks
    private OrderItemService orderItemService;

    @Mock
    private OrderItemRepository orderItemRepository;

    public OrderItemServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrderItems() {
        Orderitem orderItem = new Orderitem.Builder()
                                .setId_orderitem(1L)
                                .setId_order(1L)
                                .setId_product(1L)
                                .setSku("SKU123")
                                .setAmount(2L)
                                .build();

        List<Orderitem> orderItems = List.of(orderItem);
        when(orderItemRepository.findAll()).thenReturn(orderItems);

        List<Orderitem> result = orderItemService.getAllOrderItems();

        assertEquals(orderItems, result);
        verify(orderItemRepository).findAll();
    }

    @Test
    void testGetOrderItemByIdFound() {
        Orderitem orderItem = new Orderitem.Builder()
                                .setId_orderitem(1L)
                                .setId_order(1L)
                                .setId_product(1L)
                                .setSku("SKU123")
                                .setAmount(2L)
                                .build();

        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItem));

        Optional<Orderitem> result = orderItemService.getOrderItemById(1L);

        assertTrue(result.isPresent());
        assertEquals(orderItem, result.get());
    }

    @Test
    void testSaveOrderItem() {
        Orderitem orderItem = new Orderitem.Builder()
                                .setId_orderitem(1L)
                                .setId_order(1L)
                                .setId_product(1L)
                                .setSku("SKU123")
                                .setAmount(2L)
                                .build();

        when(orderItemRepository.save(orderItem)).thenReturn(orderItem);

        Orderitem result = orderItemService.saveOrderItem(orderItem);

        assertEquals(orderItem, result);
        verify(orderItemRepository).save(orderItem);
    }
}
