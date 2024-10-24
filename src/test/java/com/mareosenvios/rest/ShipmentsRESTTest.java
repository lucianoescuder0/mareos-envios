package com.mareosenvios.rest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import com.mareosenvios.dto.request.ShippingStateUpdateDTO;
import com.mareosenvios.dto.response.ResponseServiceDTO;
import com.mareosenvios.dto.response.ShippingDetailsDTO;
import com.mareosenvios.dto.response.StateDTO;
import com.mareosenvios.service.ShipmentsService;
import com.mareosenvios.service.StatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ShipmentsREST.class)
public class ShipmentsRESTTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentsService shipmentsService;

    @MockBean
    private StatesService statesService;


    @Test
    public void testGetShipment_Success() throws Exception {
        ShippingDetailsDTO shipmentDetails = new ShippingDetailsDTO();
        shipmentDetails.setShippingId(1);
        shipmentDetails.setState("Inicial");
        shipmentDetails.setSendDate(LocalDate.now());
        shipmentDetails.setPriority(1);

        ResponseServiceDTO<ShippingDetailsDTO> responseServiceDTO = new ResponseServiceDTO<>(true, shipmentDetails);


        when(shipmentsService.getShipment(1)).thenReturn(responseServiceDTO);

        mockMvc.perform(get("/api/shipments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.shippingId").value(1))
                .andExpect(jsonPath("$.data.state").value("Inicial"));
    }

    @Test
    public void testUpdateShipment_Success() throws Exception {
        ShippingStateUpdateDTO updateDTO = new ShippingStateUpdateDTO(2);

        when(shipmentsService.updateShipment(anyInt(), any(ShippingStateUpdateDTO.class))).thenReturn(new ResponseServiceDTO<>(true, ""));

        mockMvc.perform(put("/api/shipments/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"state\":2}"))
                .andExpect(status().isOk());
    }


    @Test
    public void testGetStates_Success() throws Exception {
        StateDTO state1 = new StateDTO(1, "Inicial");
        StateDTO state2 = new StateDTO(4, "Entregado");

        List<StateDTO> states = Arrays.asList(state1, state2);

        ResponseServiceDTO<List<StateDTO>> responseServiceDTO = new ResponseServiceDTO<>(true, states);

        when(statesService.getStates()).thenReturn(responseServiceDTO);

        mockMvc.perform(get("/api/shipments/states")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].code").value(1))
                .andExpect(jsonPath("$.data[0].description").value("Inicial"))
                .andExpect(jsonPath("$.data[1].code").value(4))
                .andExpect(jsonPath("$.data[1].description").value("Entregado"));
    }
}
