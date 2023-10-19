package com.workshop.postal.models.enums;

public enum TipoEmpleado {
    CONDUCTOR(0),
    REPARTIDOR(1),
    COORDINADOR(2);

    private final int valor;

    TipoEmpleado(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}




