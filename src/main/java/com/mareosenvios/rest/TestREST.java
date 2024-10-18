package com.mareosenvios.rest;

import com.mareosenvios.dto.RespuestaServicioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class TestREST {


    @GetMapping()
    public ResponseEntity<RespuestaServicioDTO> test() {
        RespuestaServicioDTO respuesta = new RespuestaServicioDTO();
        return ResponseEntity.ok(respuesta);
    }
}
