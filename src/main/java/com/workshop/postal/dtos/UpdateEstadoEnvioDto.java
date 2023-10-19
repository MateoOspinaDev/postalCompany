package com.workshop.postal.dtos;

import com.workshop.postal.models.enums.EstadoEnvio;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateEstadoEnvioDto {
    private String numeroGuia;
    private EstadoEnvio estadoEnvio;
    private String cedulaEmpleado;
}
