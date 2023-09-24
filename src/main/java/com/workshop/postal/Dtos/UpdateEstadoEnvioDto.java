package com.workshop.postal.Dtos;

import com.workshop.postal.models.enums.EstadoEnvio;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateEstadoEnvioDto {
    private String numeroGuia;
    private EstadoEnvio estadoEnvio;
    private String cedulaEmpleado;
}
