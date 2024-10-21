package com.mareosenvios.service;
import com.mareosenvios.dto.ResponseServiceDTO;
import com.mareosenvios.dto.StateDTO;
import com.mareosenvios.enums.ShippingStatus;
import com.mareosenvios.utils.ExParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StatesService {

    private static final Logger logger = LoggerFactory.getLogger(StatesService.class);


    public ResponseServiceDTO<List<StateDTO>> getStates() {
        try{
            List<StateDTO> statusList = Arrays.asList(ShippingStatus.values()).stream().map(StateDTO::new).collect(Collectors.toList());
            if(!statusList.isEmpty()){
                return new ResponseServiceDTO<>(true, "", statusList);
            } else {
                return new ResponseServiceDTO<>(true, "No hay estados disponibles en este momento");
            }
        }
        catch (Exception e) {
            String message = ExParser.getRootException(e).getMessage();
            logger.error("Error al recuperar los estados - CAUSA: {}", message, e);
            return new ResponseServiceDTO<>(false, message);
        }
    }

    // valida el sig estado de un envio
    public boolean validateNextState(ShippingStatus currentStatus, ShippingStatus newStatus) {
        switch (currentStatus) {
            case INITIAL:
                return newStatus == ShippingStatus.DELIVERED_TO_MAIL || newStatus == ShippingStatus.CANCELED;
            case DELIVERED_TO_MAIL:
                return newStatus == ShippingStatus.IN_TRANSIT || newStatus == ShippingStatus.CANCELED;
            case IN_TRANSIT:
                return newStatus == ShippingStatus.DELIVERED;
            case DELIVERED:
            case CANCELED:
                return false;
            default:
                throw new IllegalArgumentException("Cod de estado invalido: " + newStatus);
        }
    }
}
