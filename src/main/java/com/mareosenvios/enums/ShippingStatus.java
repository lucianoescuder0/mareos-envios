package com.mareosenvios.enums;

public enum ShippingStatus {

    CANCELED(0, "Cancelado"),
    INITIAL(1, "Inicial"),
    DELIVERED_TO_MAIL(2, "Entregado al correo"),
    IN_TRANSIT(3, "En camino"),
    DELIVERED(4, "Entregado");

    private final int code;
    private final String description;

    ShippingStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ShippingStatus fromCode(int code) {
        for (ShippingStatus status : ShippingStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Cod de estado invalido: " + code);
    }

    public static ShippingStatus fromDescription(String description) {
        for (ShippingStatus status : ShippingStatus.values()) {
            if (status.getDescription().equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("El estado con la descripción '" + description + "' no se encuentra");
    }
}
