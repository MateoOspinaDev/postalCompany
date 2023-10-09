package com.workshop.postal.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioDto {
    private String cedulaCliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String direccionDestino;
    private String nombreRecibe;
    private String celular;
    private double valorDeclaradoPaquete;
    private double peso;
}

