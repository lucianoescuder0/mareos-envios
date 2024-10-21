package com.mareosenvios.rest;

import com.mareosenvios.dto.ResponseServiceDTO;
import com.mareosenvios.service.ShipmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipments")
public class ShipmentsREST {

    @Autowired
    private ShipmentsService shipmentsService;

    @GetMapping("/{shipmentId}")
    public ResponseEntity<ResponseServiceDTO> getCustomer(@PathVariable("shipmentId") Integer shipmentId) {
        ResponseServiceDTO response = this.shipmentsService.getShipment(shipmentId);
        return ResponseEntity.ok(response);
    }
}
