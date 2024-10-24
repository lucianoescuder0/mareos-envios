package com.mareosenvios.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.mareosenvios.dto.response.CustomerDTO;
import com.mareosenvios.dto.response.ResponseServiceDTO;
import com.mareosenvios.dto.response.TopProductDTO;
import com.mareosenvios.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(controllers = ProductsREST.class)
public class ProductsRESTTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;


    @Test
    public void testTopNProducts_Success() throws Exception {
        TopProductDTO product1 = new TopProductDTO("Producto 1", 10L);
        TopProductDTO product2 = new TopProductDTO("Producto 2", 5L);
        List<TopProductDTO> topProducts = Arrays.asList(product1, product2);

        ResponseServiceDTO<List<TopProductDTO>> responseServiceDTO = new ResponseServiceDTO<>(true, topProducts);


        when(this.productsService.topNProducts(anyInt())).thenReturn(responseServiceDTO);

        // Realizar la solicitud GET y verificar la respuesta
        mockMvc.perform(get("/api/products/top-products")
                        .param("top", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].description").value("Producto 1"))
                .andExpect(jsonPath("$.data[0].productCount").value(10))
                .andExpect(jsonPath("$.data[1].description").value("Producto 2"))
                .andExpect(jsonPath("$.data[1].productCount").value(5));
    }

}