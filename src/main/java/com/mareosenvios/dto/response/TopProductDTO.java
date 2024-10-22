package com.mareosenvios.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Representa la cantidad de veces que se pidio un producto")
public class TopProductDTO implements Serializable {

    @ApiModelProperty(value = "Descripción del producto")
    private String description;

    @ApiModelProperty(value = "Cantidad de veces que se pidio el producto")
    private Long productCount;

    public TopProductDTO(String description, Long productCount) {
        this.description = description;
        this.productCount = productCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }
}
