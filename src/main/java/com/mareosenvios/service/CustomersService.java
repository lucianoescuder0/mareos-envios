package com.mareosenvios.service;

import com.mareosenvios.dto.response.CustomerDTO;
import com.mareosenvios.dto.response.ResponseServiceDTO;
import com.mareosenvios.entities.Customer;
import com.mareosenvios.repositories.CustomerRepository;
import com.mareosenvios.utils.ExParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CustomersService {

    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomersService.class);


    public ResponseServiceDTO<CustomerDTO> getCustomer(Integer customerId) {
        try {
            return this.findCustomerById(customerId);
        } catch (Exception e) {
            String message = ExParser.getRootException(e).getMessage();
            logger.error("Error al recuperar el cliente con identificador: {} - ERROR: {}", customerId, message, e);
            return new ResponseServiceDTO<>(false, message);
        }
    }

    private ResponseServiceDTO<CustomerDTO> findCustomerById(Integer customerId) {
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    logger.warn("No existe el cliente con el identificador {}", customerId);
                    return new EntityNotFoundException("No existe el cliente con el identificador " + customerId);
                });
        CustomerDTO customerDTO = new CustomerDTO(customer);
        logger.info("Cliente encontrado: {}", customerDTO);
        return new ResponseServiceDTO<>(true, "", customerDTO);
    }

}
