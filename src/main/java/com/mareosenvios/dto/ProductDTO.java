package com.mareosenvios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mareosenvios.entities.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Representa la información de un producto")
public class ProductDTO implements Serializable {
    @ApiModelProperty(value = "ID del producto", required = true)
    private Integer productId;
    @ApiModelProperty(value = "Descripción del producto")
    private String description;
    @ApiModelProperty(value = "Peso del producto")
    private Double weight;
    @ApiModelProperty(value = "Cantidad del producto en un envio")
    private Integer productCount;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.productId = product.getId();
        this.description = product.getDescription();
        this.weight = product.getWeight();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
