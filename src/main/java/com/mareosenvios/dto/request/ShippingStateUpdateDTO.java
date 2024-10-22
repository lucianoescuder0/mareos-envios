package com.mareosenvios.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "DTO para actualizar el estado de un envio")
public class ShippingStateUpdateDTO implements Serializable {
    @NotNull(message = "state es obligatorio")
    @ApiModelProperty(value = "El nuevo estado del envio", example = "1")
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
