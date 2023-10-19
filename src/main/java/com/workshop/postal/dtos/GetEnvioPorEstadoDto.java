package com.workshop.postal.dtos;

import com.workshop.postal.models.enums.EstadoEnvio;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEnvioPorEstadoDto {
    private String cedulaEmpleado;
    private EstadoEnvio estadoEnvio;
}
