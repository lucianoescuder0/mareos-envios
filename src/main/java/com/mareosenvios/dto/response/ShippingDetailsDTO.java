package com.mareosenvios.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mareosenvios.entities.Shipping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Representa el detalle completo de un envio")
public class ShippingDetailsDTO implements Serializable {
    @ApiModelProperty(value = "ID del envio", required = true)
    private Integer shippingId;
    @ApiModelProperty(value = "Estado del envio")
    private String state;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @ApiModelProperty(value = "Fecha de envio")
    private LocalDate sendDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @ApiModelProperty(value = "Fecha de llegada")
    private LocalDate arriveDate;
    @ApiModelProperty(value = "Prioridad del envio")
    private Integer priority;
    @ApiModelProperty(value = "Cliente")
    private CustomerDTO customer;
    @ApiModelProperty(value = "Productos en el envio")
    private List<ProductDTO> products;

    public ShippingDetailsDTO() {
    }

    public ShippingDetailsDTO(Shipping shipping) {
        this.shippingId = shipping.getId();
        this.state = shipping.getState();
        this.sendDate = shipping.getSendDate();
        this.arriveDate = shipping.getArriveDate();
        this.priority = shipping.getPriority();
        if(shipping.getCustomer() != null) {
            this.customer = new CustomerDTO(shipping.getCustomer());
        }
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
