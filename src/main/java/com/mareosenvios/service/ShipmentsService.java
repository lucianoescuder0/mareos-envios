package com.mareosenvios.service;

import com.mareosenvios.dto.request.ShippingStateUpdateDTO;
import com.mareosenvios.dto.response.ProductDTO;
import com.mareosenvios.dto.response.ResponseServiceDTO;
import com.mareosenvios.dto.response.ShippingDetailsDTO;
import com.mareosenvios.entities.Shipping;
import com.mareosenvios.entities.ShippingItem;
import com.mareosenvios.enums.ShippingStatus;
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

    @Autowired
    private StatesService statesService;

    private static final Logger logger = LoggerFactory.getLogger(ShipmentsService.class);


    public ResponseServiceDTO<ShippingDetailsDTO> getShipment(Integer shipmentId) {
        try {
            return this.findShipmentDetailsById(shipmentId);
        } catch (Exception e) {
            String message = ExParser.getRootException(e).getMessage();
            logger.error("Error al recuperar el envio con identificador: {} - CAUSA: {}", shipmentId, message, e);
            return new ResponseServiceDTO<>(false, message);
        }
    }


    public ResponseServiceDTO<?> updateShipment(Integer shipmentId, ShippingStateUpdateDTO newStatusCode) {
        try {
            Shipping shipping = shippingRepository.findById(shipmentId)
                    .orElseThrow(() -> new EntityNotFoundException("No existe el envio con el identificador " + shipmentId));

            ShippingStatus newStatus = ShippingStatus.fromCode(newStatusCode.getState());
            if (shipping.getStatusEnum() == newStatus) {
                return new ResponseServiceDTO<>(false, "El envío con identificador: " + shipmentId + " ya esta en el estado: " + newStatus.getDescription());
            }
            return this.updateStateShipment(shipping, newStatus);
        } catch (Exception e) {
            String message = ExParser.getRootException(e).getMessage();
            logger.error("No se pudo actualizar el envio con identificador: {} - CAUSA: {}", shipmentId, message, e);
            return new ResponseServiceDTO<>(false, message);
        }
    }

    private ResponseServiceDTO<ShippingDetailsDTO> updateStateShipment(Shipping shipping, ShippingStatus newStatus) {
        if(this.statesService.validateNextState(shipping.getStatusEnum(), newStatus)){
            shipping.setState(newStatus.getDescription());
            this.shippingRepository.save(shipping);
            return new ResponseServiceDTO<>(true, "El envio con identificador: " + shipping.getId() + " fue actualizado correctamente");
        } else {
            return new ResponseServiceDTO<>(false, "El envio con identificador: " + shipping.getId() + " no puede ser modificado al estado: " + newStatus.getDescription());
        }
    }


    private ResponseServiceDTO<ShippingDetailsDTO> findShipmentDetailsById(Integer shipmentId) {
        Shipping shipping = shippingRepository.findById(shipmentId)
                .orElseThrow(() -> new EntityNotFoundException("No existe el envio con el identificador " + shipmentId));

        ShippingDetailsDTO shippingDetailsDTO = new ShippingDetailsDTO(shipping);

        List<ShippingItem> shippingItems = this.shippingItemRepository.findAllByShipping(shipping);
        if (shippingItems != null && !shippingItems.isEmpty()) {
            List<ProductDTO> productDTOList = shippingItems.stream().map(s -> {
                ProductDTO productDTO = new ProductDTO(s.getProduct());
                productDTO.setProductCount(s.getProductCount());
                return productDTO;
            }).collect(Collectors.toList());
            shippingDetailsDTO.setProducts(productDTOList);
            logger.info("Se encontraron {} productos para el envio {}", shippingItems.size(), shipmentId);
            return new ResponseServiceDTO<>(true, "", shippingDetailsDTO);
        } else {
            logger.warn("No se encontraron productos para el envio con el identificador {}", shipmentId);
            return new ResponseServiceDTO<>(true, "No se encontraron productos para el envio con el identificador " + shipmentId);
        }
    }
}
