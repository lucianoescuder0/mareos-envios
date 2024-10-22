package com.mareosenvios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mareosenvios.enums.ShippingStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Representa el los estados que puede tener un envio")
public class StateDTO implements Serializable {
    @ApiModelProperty(value = "Código del estado")
    Integer code;
    @ApiModelProperty(value = "Descripción del estado")
    String description;

    public StateDTO(ShippingStatus shippingStatus) {
        this.code = shippingStatus.getCode();
        this.description = shippingStatus.getDescription();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
