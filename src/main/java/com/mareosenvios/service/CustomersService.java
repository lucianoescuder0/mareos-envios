package com.mareosenvios.service;

import com.mareosenvios.dto.CustomerDTO;
import com.mareosenvios.dto.RespuestaServicioDTO;
import com.mareosenvios.entities.Customer;
import com.mareosenvios.repositories.CustomerRepository;
import com.mareosenvios.utils.ExParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomersService {

    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomersService.class);


    public RespuestaServicioDTO getCustomer(Integer customerId) {
        try {
            return this.findCustomerById(customerId);
        } catch (Exception e) {
            logger.error("Error al recuperar el cliente con id: {} - ERROR: {}", customerId, e.getMessage());
            return new RespuestaServicioDTO(false, ExParser.getRootException(e).getMessage());
        }
    }

    private RespuestaServicioDTO findCustomerById(Integer customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = new CustomerDTO(customer.get());
            logger.info("Cliente encontrado: {}", customerDTO);
            return new RespuestaServicioDTO(true, "", customerDTO);
        } else {
            logger.warn("No existe el cliente con el identificador {}", customerId);
            return new RespuestaServicioDTO(false, "No existe el cliente con el identificador " + customerId);
        }
    }
}
