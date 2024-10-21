package com.mareosenvios.rest;

import com.mareosenvios.dto.CustomerDTO;
import com.mareosenvios.dto.ResponseServiceDTO;
import com.mareosenvios.service.CustomersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@Api(value = "CustomersREST", description = "Endpoints de clientes")
public class CustomersREST {

    @Autowired
    private CustomersService customersService;

    @GetMapping("/{customerId}")
    @ApiOperation(value = "Obtener un cliente por id", response = ResponseServiceDTO.class)
    public ResponseEntity<ResponseServiceDTO<CustomerDTO>> getCustomer(
            @ApiParam(value = "id del cliente", required = true)
            @PathVariable("customerId") Integer customerId) {
        ResponseServiceDTO<CustomerDTO> response = this.customersService.getCustomer(customerId);
        return ResponseEntity.ok(response);
    }
}
