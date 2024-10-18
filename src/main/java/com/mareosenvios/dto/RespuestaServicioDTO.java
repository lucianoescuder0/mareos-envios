package com.mareosenvios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaServicioDTO implements Serializable {

    private Boolean exito;
    private String mensaje;
    private Object datos;

    public RespuestaServicioDTO() {
    }

    public RespuestaServicioDTO(Boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }

    public RespuestaServicioDTO(Boolean exito, String mensaje, Object datos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public Boolean getExito() {
        return exito;
    }

    public void setExito(Boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }


    @Override
    public String toString() {
        return "RespuestaServicioDTO{" + "exito=" + exito + ", mensaje=" + mensaje + ", datos=" + datos + '}';
    }

}
