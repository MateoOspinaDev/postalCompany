package com.workshop.postal.Dtos;

import com.workshop.postal.models.enums.EstadoEnvio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnvioRecibidoDto {
    public String numeroGuia;
    public EstadoEnvio estadoEnvio;
}
