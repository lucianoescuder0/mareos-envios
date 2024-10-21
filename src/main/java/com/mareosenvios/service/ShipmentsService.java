package com.mareosenvios.service;

import com.mareosenvios.dto.ProductDTO;
import com.mareosenvios.dto.ResponseServiceDTO;
import com.mareosenvios.dto.ShippingDetailsDTO;
import com.mareosenvios.entities.Shipping;
import com.mareosenvios.entities.ShippingItem;
import com.mareosenvios.repositories.ShippingItemRepository;
import com.mareosenvios.repositories.ShippingRepository;
import com.mareosenvios.utils.ExParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentsService {

    @Autowired
    private ShippingItemRepository shippingItemRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    private static final Logger logger = LoggerFactory.getLogger(ShipmentsService.class);


    public ResponseServiceDTO getShipment(Integer shipmentId) {
        try {
            return this.findShipmentById(shipmentId);
        } catch (Exception e) {
            logger.error("Error al recuperar el envio con id: {} - ERROR: {}", shipmentId, e.getMessage());
            return new ResponseServiceDTO(false, ExParser.getRootException(e).getMessage());
        }
    }

    private ResponseServiceDTO findShipmentById(Integer shipmentId) {
        Shipping shipping = shippingRepository.findById(shipmentId)
                .orElseThrow(() -> new EntityNotFoundException("No existe el envío con el identificador " + shipmentId));

        ShippingDetailsDTO shippingDetailsDTO = new ShippingDetailsDTO(shipping);

        List<ShippingItem> shippingItems = this.shippingItemRepository.findAllByShipping(shipping);
        if (shippingItems != null && !shippingItems.isEmpty()) {
            List<ProductDTO> productDTOList = shippingItems.stream().map(s -> {
                ProductDTO productDTO = new ProductDTO(s.getProduct());
                productDTO.setProductCount(s.getProductCount());
                return productDTO;
            }).collect(Collectors.toList());
            shippingDetailsDTO.setProducts(productDTOList);
            logger.info("Se encontraron {} productos para el envío {}", shippingItems.size(), shipmentId);
            return new ResponseServiceDTO(true, "", shippingDetailsDTO);
        } else {
            logger.warn("No se encontraron productos para el envío con el identificador {}", shipmentId);
            return new ResponseServiceDTO(true, "No se encontraron productos para el envio con el identificador " + shipmentId);
        }
    }
}
