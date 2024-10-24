package com.mareosenvios.service;

import com.mareosenvios.dto.request.ShippingStateUpdateDTO;
import com.mareosenvios.dto.response.ResponseServiceDTO;
import com.mareosenvios.dto.response.ShippingDetailsDTO;
import com.mareosenvios.entities.Shipping;
import com.mareosenvios.entities.ShippingItem;
import com.mareosenvios.enums.ShippingStatus;
import com.mareosenvios.repositories.ShippingItemRepository;
import com.mareosenvios.repositories.ShippingRepository;
import com.mareosenvios.service.ShipmentsService;
import com.mareosenvios.service.StatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShipmentsServiceTest {

    @Mock
    private ShippingItemRepository shippingItemRepository;

    @Mock
    private ShippingRepository shippingRepository;

    @Mock
    private StatesService statesService;

    @InjectMocks
    private ShipmentsService shipmentsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getShipment_SuccessfulRetrieval() {
        Integer shipmentId = 1;
        Shipping shipping = mock(Shipping.class);
        when(shippingRepository.findById(shipmentId)).thenReturn(Optional.of(shipping));
        List<ShippingItem> shippingItems = new ArrayList<>();
        shippingItems.add(mock(ShippingItem.class));
        when(shippingItemRepository.findAllByShipping(shipping)).thenReturn(shippingItems);

        ResponseServiceDTO<ShippingDetailsDTO> response = shipmentsService.getShipment(shipmentId);

        assertTrue(response.getSuccess());
        assertNotNull(response.getData());
        verify(shippingRepository, times(1)).findById(shipmentId);
        verify(shippingItemRepository, times(1)).findAllByShipping(shipping);
    }

    @Test
    void getShipment_EntityNotFound() {
        Integer shipmentId = 1;
        when(shippingRepository.findById(shipmentId)).thenReturn(Optional.empty());

        ResponseServiceDTO<ShippingDetailsDTO> response = shipmentsService.getShipment(shipmentId);

        assertFalse(response.getSuccess());
        assertEquals("No existe el envio con el identificador " + shipmentId, response.getMessage());
        verify(shippingRepository, times(1)).findById(shipmentId);
    }

    @Test
    void updateShipment_SuccessfulUpdate() {
        Integer shipmentId = 1;
        Shipping shipping = mock(Shipping.class);
        when(shipping.getStatusEnum()).thenReturn(ShippingStatus.INITIAL);
        when(shippingRepository.findById(shipmentId)).thenReturn(Optional.of(shipping));
        ShippingStateUpdateDTO newStatusCode = new ShippingStateUpdateDTO(3); // IN_TRANSIT
        ShippingStatus newStatus = ShippingStatus.IN_TRANSIT;
        when(statesService.validateNextState(ShippingStatus.INITIAL, newStatus)).thenReturn(true);

        ResponseServiceDTO<?> response = shipmentsService.updateShipment(shipmentId, newStatusCode);

        assertTrue(response.getSuccess());
        verify(shippingRepository, times(1)).findById(shipmentId);
        verify(statesService, times(1)).validateNextState(ShippingStatus.INITIAL, newStatus);
        verify(shippingRepository, times(1)).save(shipping);
    }

    @Test
    void updateShipment_NoStateChange() {
        Integer shipmentId = 1;
        Shipping shipping = mock(Shipping.class);
        when(shipping.getStatusEnum()).thenReturn(ShippingStatus.INITIAL);
        when(shippingRepository.findById(shipmentId)).thenReturn(Optional.of(shipping));
        ShippingStateUpdateDTO newStatusCode = new ShippingStateUpdateDTO(1); // INITIAL

        ResponseServiceDTO<?> response = shipmentsService.updateShipment(shipmentId, newStatusCode);

        assertFalse(response.getSuccess());
        assertEquals("El envío con identificador: " + shipmentId + " ya esta en el estado: " + ShippingStatus.INITIAL.getDescription(), response.getMessage());
        verify(shippingRepository, times(1)).findById(shipmentId);
        verifyNoMoreInteractions(shippingRepository);
    }

    @Test
    void updateShipment_InvalidStateTransition() {
        Integer shipmentId = 1;
        Shipping shipping = mock(Shipping.class);
        when(shipping.getStatusEnum()).thenReturn(ShippingStatus.INITIAL);
        when(shippingRepository.findById(shipmentId)).thenReturn(Optional.of(shipping));
        ShippingStateUpdateDTO newStatusCode = new ShippingStateUpdateDTO(4); // DELIVERED
        ShippingStatus newStatus = ShippingStatus.DELIVERED;
        when(statesService.validateNextState(ShippingStatus.INITIAL, newStatus)).thenReturn(false);

        ResponseServiceDTO<?> response = shipmentsService.updateShipment(shipmentId, newStatusCode);

        assertFalse(response.getSuccess());
        assertEquals("El envio con identificador: " + shipping.getId() +  " con estado: " + shipping.getState() + " no puede ser modificado al estado: " + ShippingStatus.DELIVERED.getDescription(), response.getMessage());
        verify(shippingRepository, times(1)).findById(shipmentId);
        verify(statesService, times(1)).validateNextState(ShippingStatus.INITIAL, newStatus);
        verifyNoMoreInteractions(shippingRepository);
    }

    @Test
    void updateShipment_EntityNotFound() {
        Integer shipmentId = 1;
        when(shippingRepository.findById(shipmentId)).thenReturn(Optional.empty());
        ShippingStateUpdateDTO newStatusCode = new ShippingStateUpdateDTO(3); // IN_TRANSIT

        ResponseServiceDTO<?> response = shipmentsService.updateShipment(shipmentId, newStatusCode);

        assertFalse(response.getSuccess());
        assertEquals("No existe el envio con el identificador " + shipmentId, response.getMessage());
        verify(shippingRepository, times(1)).findById(shipmentId);
    }
}
