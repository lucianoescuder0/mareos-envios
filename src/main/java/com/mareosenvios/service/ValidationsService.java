package com.mareosenvios.service;
import com.mareosenvios.enums.ShippingStatus;
import org.springframework.stereotype.Service;


@Service
public class ValidationsService {


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
