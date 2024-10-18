package com.mareosenvios.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class TestREST {

    @GetMapping()
    public String test() {
        return "test";
    }
}
