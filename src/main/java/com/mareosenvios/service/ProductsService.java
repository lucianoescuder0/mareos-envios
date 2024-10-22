package com.mareosenvios.service;

import com.mareosenvios.dto.ResponseServiceDTO;
import com.mareosenvios.dto.TopProductDTO;
import com.mareosenvios.repositories.ShippingItemRepository;
import com.mareosenvios.utils.ExParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private ShippingItemRepository shippingItemRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);


    public ResponseServiceDTO<List<TopProductDTO>> topNProducts(Integer top) {
        try{
            if(top <= 0) {
                return new ResponseServiceDTO<>(false, "El top debe ser mayor que 0");
            }
            return this.findTopNProducts(top);
        } catch (Exception e) {
            String message = ExParser.getRootException(e).getMessage();
            logger.error("Error al recuperar el listado de productos - CAUSA: {}", message, e);
            return new ResponseServiceDTO<>(false, message);
        }
    }

    private ResponseServiceDTO<List<TopProductDTO>> findTopNProducts(int top) {
        List<TopProductDTO> productDTOList = this.shippingItemRepository.findTopProducts();
        if(!productDTOList.isEmpty()){
            return new ResponseServiceDTO<>(true, "", productDTOList.stream()
                    .limit(top)
                    .collect(Collectors.toList()));
        } else {
            return new ResponseServiceDTO<>(false, "No se encontraron productos");
        }
    }
}
