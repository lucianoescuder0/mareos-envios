package com.mareosenvios.rest;

import com.mareosenvios.dto.ResponseServiceDTO;
import com.mareosenvios.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersREST {

    @Autowired
    private CustomersService customersService;

    @GetMapping("/{customerId}")
    public ResponseEntity<ResponseServiceDTO> getCustomer(@PathVariable("customerId") Integer customerId) {
        ResponseServiceDTO response = this.customersService.getCustomer(customerId);
        return ResponseEntity.ok(response);
    }
}
