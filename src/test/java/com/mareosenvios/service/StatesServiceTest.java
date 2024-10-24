package com.mareosenvios.service;

import com.mareosenvios.enums.ShippingStatus;
import com.mareosenvios.service.StatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatesServiceTest {

    private StatesService statesService;

    @BeforeEach
    void setUp() {
        statesService = new StatesService();
    }

    @Test
    void testValidateNextState_InitialToDeliveredToMail() {
        ShippingStatus currentStatus = ShippingStatus.INITIAL;
        ShippingStatus newStatus = ShippingStatus.DELIVERED_TO_MAIL;

        boolean result = statesService.validateNextState(currentStatus, newStatus);

        assertTrue(result);
    }

    @Test
    void testValidateNextState_InitialToCanceled() {
        ShippingStatus currentStatus = ShippingStatus.INITIAL;
        ShippingStatus newStatus = ShippingStatus.CANCELED;

        boolean result = statesService.validateNextState(currentStatus, newStatus);

        assertTrue(result);
    }

    @Test
    void testValidateNextState_DeliveredToMailToInTransit() {
        ShippingStatus currentStatus = ShippingStatus.DELIVERED_TO_MAIL;
        ShippingStatus newStatus = ShippingStatus.IN_TRANSIT;

        boolean result = statesService.validateNextState(currentStatus, newStatus);

        assertTrue(result);
    }

    @Test
    void testValidateNextState_InTransitToDelivered() {
        ShippingStatus currentStatus = ShippingStatus.IN_TRANSIT;
        ShippingStatus newStatus = ShippingStatus.DELIVERED;

        boolean result = statesService.validateNextState(currentStatus, newStatus);

        assertTrue(result);
    }

    @Test
    void testValidateNextState_DeliveredToAnyState() {
        ShippingStatus currentStatus = ShippingStatus.DELIVERED;

        boolean resultToInitial = statesService.validateNextState(currentStatus, ShippingStatus.INITIAL);
        boolean resultToCanceled = statesService.validateNextState(currentStatus, ShippingStatus.CANCELED);

        assertFalse(resultToInitial);
        assertFalse(resultToCanceled);
    }

    @Test
    void testValidateNextState_InvalidStateTransition() {
        ShippingStatus currentStatus = ShippingStatus.IN_TRANSIT;
        ShippingStatus newStatus = ShippingStatus.CANCELED;

        boolean result = statesService.validateNextState(currentStatus, newStatus);

        assertFalse(result);
    }
}
