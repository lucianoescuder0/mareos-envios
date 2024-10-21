package com.mareosenvios.rest;

import com.mareosenvios.dto.ResponseServiceDTO;
import com.mareosenvios.dto.ShippingDetailsDTO;
import com.mareosenvios.dto.StatesDTO;
import com.mareosenvios.enums.ShippingStatus;
import com.mareosenvios.service.ShipmentsService;
import com.mareosenvios.service.StatesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
@Api(value = "ShipmentsREST", description = "Endpoints de envios")
public class ShipmentsREST {

    @Autowired
    private ShipmentsService shipmentsService;

    @Autowired
    private StatesService statesService;

    @GetMapping("/{shipmentId}")
    @ApiOperation(value = "Obtener el detalle completo de un envio por id", response = ResponseServiceDTO.class)
    public ResponseEntity<ResponseServiceDTO<ShippingDetailsDTO>> getShipment(
            @ApiParam(value = "id del envio", required = true)
            @PathVariable("shipmentId") Integer shipmentId) {
        ResponseServiceDTO<ShippingDetailsDTO> response = this.shipmentsService.getShipment(shipmentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{shipmentId}")
    @ApiOperation(value = "Actualiza el estaod del envio por id", response = ResponseServiceDTO.class)
    public ResponseEntity<ResponseServiceDTO<?>> updateShipment(
            @ApiParam(value = "id del envio", required = true)
            @PathVariable("shipmentId") Integer shipmentId,
            @ApiParam(value = "nuevo estado del envío", required = true)
            @RequestParam("newStatusCode") Integer newStatusCode
    ) {
        ResponseServiceDTO<?> response = this.shipmentsService.updateShipment(shipmentId, newStatusCode);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/states")
    @ApiOperation(value = "Devuelve los estados aceptados por la api", response = ResponseServiceDTO.class)
    public ResponseEntity<ResponseServiceDTO<List<StatesDTO>>> getStates() {
        ResponseServiceDTO<List<StatesDTO>> response = this.statesService.getStates();
        return ResponseEntity.ok(response);
    }
}
