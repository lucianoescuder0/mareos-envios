package com.mareosenvios.rest;

import com.mareosenvios.dto.ResponseServiceDTO;
import com.mareosenvios.dto.ShippingDetailsDTO;
import com.mareosenvios.service.ShipmentsService;
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
@RequestMapping("/shipments")
@Api(value = "ShipmentsREST", description = "Endpoints de envios")
public class ShipmentsREST {

    @Autowired
    private ShipmentsService shipmentsService;

    @GetMapping("/{shipmentId}")
    @ApiOperation(value = "Obtener el detalle completo de un envio por id", response = ResponseServiceDTO.class)
    public ResponseEntity<ResponseServiceDTO<ShippingDetailsDTO>> getCustomer(
            @ApiParam(value = "id del envio", required = true)
            @PathVariable("shipmentId") Integer shipmentId) {
        ResponseServiceDTO<ShippingDetailsDTO> response = this.shipmentsService.getShipment(shipmentId);
        return ResponseEntity.ok(response);
    }
}
