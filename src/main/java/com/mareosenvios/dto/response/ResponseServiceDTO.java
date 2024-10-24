package com.mareosenvios.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseServiceDTO<T> implements Serializable {

    @ApiModelProperty(value = "Indica si la operación fue exitosa")
    private Boolean success;

    @ApiModelProperty(value = "Mensaje de respuesta")
    private String message;

    @ApiModelProperty(value = "Datos devueltos por el servicio")
    private T data;

    public ResponseServiceDTO() {
    }

    public ResponseServiceDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseServiceDTO(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseServiceDTO(Boolean success, T data) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseService{" + "success=" + success + ", message=" + message + ", data=" + data + '}';
    }

}
