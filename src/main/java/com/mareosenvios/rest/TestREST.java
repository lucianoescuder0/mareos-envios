package com.mareosenvios.rest;

import com.mareosenvios.dto.ResponseServiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class TestREST {


    @GetMapping()
    public ResponseEntity<ResponseServiceDTO> test() {
        ResponseServiceDTO response = new ResponseServiceDTO();
        return ResponseEntity.ok(response);
    }
}
