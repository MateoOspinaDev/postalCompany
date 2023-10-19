package com.workshop.postal.models.enums;

public enum TipoPaquete {
    LIVIANO(0),
    MEDIANO(1),
    GRANDE(2);

    private final int value;

    TipoPaquete(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}




