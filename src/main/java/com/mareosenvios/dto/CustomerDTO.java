package com.mareosenvios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mareosenvios.entities.Customer;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO implements Serializable {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;

    public CustomerDTO() {
    }
    public CustomerDTO(Integer id, String firstName, String lastName) {
        this.customerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerDTO(Customer customer) {
        this.customerId = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.address = customer.getAddress();
        this.city = customer.getCity();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
