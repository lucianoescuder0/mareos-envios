package com.mareosenvios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mareosenvios.entities.Product;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO implements Serializable {
    private Integer productId;
    private String description;
    private Double weight;
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
