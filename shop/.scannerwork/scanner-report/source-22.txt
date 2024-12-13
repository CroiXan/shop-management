package com.croix.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.croix.shop.model.Products;
import com.croix.shop.repository.CatalogRepository;

public class CatalogServiceTests {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private CatalogRepository catalogRepository;

    public CatalogServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Products product = new Products();
        product.setId_product(1L);
        product.setSku("SKU123");
        product.setName("Product1");
        product.setPrice(100L);
        product.setDiscount(10);
        product.setCategory("Category1");
        product.setDescription("Description1");
        product.setStock(50);

        List<Products> products = List.of(product);
        when(catalogRepository.findAll()).thenReturn(products);

        List<Products> result = catalogService.getAllProducts();

        assertEquals(products, result);
        verify(catalogRepository).findAll();
    }

    @Test
    void testGetProductByIdFound() {
        Products product = new Products();
        product.setId_product(1L);
        product.setSku("SKU123");
        product.setName("Product1");

        when(catalogRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Products> result = catalogService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    void testGetProductByIdNotFound() {
        when(catalogRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Products> result = catalogService.getProductById(1L);

        assertFalse(result.isPresent());
    }
}
