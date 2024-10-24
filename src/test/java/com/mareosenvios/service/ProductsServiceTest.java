package com.mareosenvios.service;

import com.mareosenvios.dto.response.TopProductDTO;
import com.mareosenvios.repositories.ShippingItemRepository;
import com.mareosenvios.service.ProductsService;
import com.mareosenvios.dto.response.ResponseServiceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductsServiceTest {

    @Mock
    private ShippingItemRepository shippingItemRepository;

    @InjectMocks
    private ProductsService productsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void topNProducts_InvalidTop() {
        Integer top = 0;

        ResponseServiceDTO<List<TopProductDTO>> response = productsService.topNProducts(top);

        assertFalse(response.getSuccess());
        assertEquals("El top debe ser mayor que 0", response.getMessage());
        verifyNoInteractions(shippingItemRepository);
    }

    @Test
    void topNProducts_ProductsFound_() {
        Integer top = 3;
        List<TopProductDTO> topProducts = new ArrayList<>();
        topProducts.add(new TopProductDTO("Producto A", 50L));
        topProducts.add(new TopProductDTO("Producto B", 40L));
        topProducts.add(new TopProductDTO("Producto C", 30L));
        topProducts.add(new TopProductDTO("Producto D", 20L));

        when(shippingItemRepository.findTopProducts()).thenReturn(topProducts);

        ResponseServiceDTO<List<TopProductDTO>> response = productsService.topNProducts(top);

        assertTrue(response.getSuccess());
        assertNotNull(response.getData());
        assertEquals(3, response.getData().size());
        assertEquals("Producto A", response.getData().get(0).getDescription());
        verify(shippingItemRepository, times(1)).findTopProducts();
    }

    @Test
    void topNProducts_NoProductsFound() {
        Integer top = 5;
        List<TopProductDTO> emptyList = new ArrayList<>();

        when(shippingItemRepository.findTopProducts()).thenReturn(emptyList);

        ResponseServiceDTO<List<TopProductDTO>> response = productsService.topNProducts(top);

        assertFalse(response.getSuccess());
        assertEquals("No se encontraron productos", response.getMessage());
        verify(shippingItemRepository, times(1)).findTopProducts();
    }

    @Test
    void topNProducts_ExceptionThrown() {
        Integer top = 5;
        when(shippingItemRepository.findTopProducts()).thenThrow(new RuntimeException("Database error"));

        ResponseServiceDTO<List<TopProductDTO>> response = productsService.topNProducts(top);

        assertFalse(response.getSuccess());
        assertEquals("Database error", response.getMessage());
        verify(shippingItemRepository, times(1)).findTopProducts();
    }
}
