package com.workshop.postal.Dtos;

import com.workshop.postal.models.enums.EstadoEnvio;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEnvioPorEstadoDto {
    private String cedulaEmpleado;
    private EstadoEnvio estadoEnvio;
}
