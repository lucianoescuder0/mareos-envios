package com.mareosenvios.rest;

import com.mareosenvios.dto.response.CustomerDTO;
import com.mareosenvios.dto.response.ResponseServiceDTO;
import com.mareosenvios.service.CustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = CustomersREST.class)
public class CustomersRESTTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomersService customersService;

    @Test
    public void testGetCustomer_Success() throws Exception {
        Integer customerId = 1;
        CustomerDTO mockCustomer = new CustomerDTO(customerId, "Lucho", "Test");
        ResponseServiceDTO<CustomerDTO> mockResponse = new ResponseServiceDTO<>(true, "", mockCustomer);

        when(this.customersService.getCustomer(customerId)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/customers/{customerId}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.customerId").value(customerId))
                .andExpect(jsonPath("$.data.firstName").value("Lucho"))
                .andExpect(jsonPath("$.data.lastName").value("Test"));
    }

    @Test
    public void testGetCustomer_NotSuccess() throws Exception {
        Integer customerId = 35;
        ResponseServiceDTO<CustomerDTO> mockResponse = new ResponseServiceDTO<>(false, "No existe el cliente con el identificador " + customerId);

        when(this.customersService.getCustomer(customerId)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/customers/{customerId}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("No existe el cliente con el identificador " + customerId));
    }
}