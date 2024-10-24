package com.mareosenvios.service;

import com.mareosenvios.dto.response.CustomerDTO;
import com.mareosenvios.entities.Customer;
import com.mareosenvios.repositories.CustomerRepository;
import com.mareosenvios.service.CustomersService;
import com.mareosenvios.dto.response.ResponseServiceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomersServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomersService customersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCustomer_CustomerExists() {
        Integer customerId = 1;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("Luciano");
        customer.setLastName("Escudero");
        customer.setAddress("Otra dire 123");
        customer.setCity("San Luis");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        ResponseServiceDTO<CustomerDTO> response = customersService.getCustomer(customerId);

        assertTrue(response.getSuccess());
        assertNotNull(response.getData());
        assertEquals("Luciano", response.getData().getFirstName());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void getCustomer_CustomerDoesNotExist() {
        Integer customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        ResponseServiceDTO<CustomerDTO> response = customersService.getCustomer(customerId);

        assertFalse(response.getSuccess());
        assertEquals("No existe el cliente con el identificador " + customerId, response.getMessage());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void getCustomer_ExceptionThrown() {
        Integer customerId = 1;
        when(customerRepository.findById(customerId)).thenThrow(new RuntimeException("Database error"));

        ResponseServiceDTO<CustomerDTO> response = customersService.getCustomer(customerId);

        assertFalse(response.getSuccess());
        assertEquals("Database error", response.getMessage());
        verify(customerRepository, times(1)).findById(customerId);
    }
}
