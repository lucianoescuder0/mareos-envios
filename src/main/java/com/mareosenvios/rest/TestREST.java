package com.mareosenvios.rest;

import com.mareosenvios.dto.ResponseServiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/prueba")
@Api(value = "Test", description = "Test descripción")
public class TestREST {

    @ApiOperation(value = "Test", response = ResponseServiceDTO.class)
    @GetMapping()
    public ResponseEntity<ResponseServiceDTO<?>> test() {
        ResponseServiceDTO<?> response = new ResponseServiceDTO<>();
        return ResponseEntity.ok(response);
    }
}
