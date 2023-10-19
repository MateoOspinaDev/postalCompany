package com.workshop.postal.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class GetEnvioDto {

    private String cedulaCliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String direccionDestino;
    private String nombreRecibe;
    private String numeroCelularReceptor;
    private double valorDeclaradoPaquete;
    private double peso;
    private double valorEnvio;
    private String estadoEnvio;
    private String numeroGuia;
}
