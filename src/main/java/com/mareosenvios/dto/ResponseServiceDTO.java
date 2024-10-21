package com.mareosenvios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseServiceDTO implements Serializable {

    private Boolean success;
    private String message;
    private Object data;

    public ResponseServiceDTO() {
    }

    public ResponseServiceDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseServiceDTO(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    public ResponseServiceDTO(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseService{" + "success=" + success + ", message=" + message + ", data=" + data + '}';
    }

}
