package com.workshop.postal.models.enums;

public enum EstadoEnvio {
    RECIBIDO("Recibido"),
    EN_RUTA("En Ruta"),
    ENTREGADO("Entregado");

    private final String estado;

    EstadoEnvio(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    // Método estático para obtener el enum a partir de una cadena
    public static EstadoEnvio fromString(String text) {
        for (EstadoEnvio estado : EstadoEnvio.values()) {
            if (estado.estado.equalsIgnoreCase(text)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado de envío no válido: " + text);
    }
}

