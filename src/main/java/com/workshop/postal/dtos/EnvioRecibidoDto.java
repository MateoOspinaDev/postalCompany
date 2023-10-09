package com.workshop.postal.dtos;

import com.workshop.postal.models.enums.EstadoEnvio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnvioRecibidoDto {
    public String numeroGuia;
    public EstadoEnvio estadoEnvio;
}
