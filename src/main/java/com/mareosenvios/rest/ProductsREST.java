package com.mareosenvios.rest;

import com.mareosenvios.dto.response.ResponseServiceDTO;
import com.mareosenvios.dto.response.TopProductDTO;
import com.mareosenvios.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api(value = "ProductsREST", description = "Endpoints de produtos")
public class ProductsREST {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/top-products")
    @ApiOperation(value = "Devuelve una lista con el top n de los productos mas solicitado para su envio, sin importar el estado del mismo", response = ResponseServiceDTO.class)
    public ResponseEntity<ResponseServiceDTO<List<TopProductDTO>>> topNProducts(
            @ApiParam(value = "Rango del top, si no se envia, el valor por defecto 3", required = false)
            @RequestParam(defaultValue = "3") Integer top) {
        ResponseServiceDTO<List<TopProductDTO>> response = this.productsService.topNProducts(top);
        return ResponseEntity.ok(response);
    }
}
