package com.mareosenvios.rest;

import com.mareosenvios.dto.request.ShippingStateUpdateDTO;
import com.mareosenvios.dto.response.ResponseServiceDTO;
import com.mareosenvios.dto.response.ShippingDetailsDTO;
import com.mareosenvios.dto.response.StateDTO;
import com.mareosenvios.service.ShipmentsService;
import com.mareosenvios.service.StatesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @Valid @RequestBody() ShippingStateUpdateDTO newShippingStatus
    ) {
        ResponseServiceDTO<?> response = this.shipmentsService.updateShipment(shipmentId, newShippingStatus);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/states")
    @ApiOperation(value = "Devuelve los estados aceptados por la api", response = ResponseServiceDTO.class)
    public ResponseEntity<ResponseServiceDTO<List<StateDTO>>> getStates() {
        ResponseServiceDTO<List<StateDTO>> response = this.statesService.getStates();
        return ResponseEntity.ok(response);
    }
}
