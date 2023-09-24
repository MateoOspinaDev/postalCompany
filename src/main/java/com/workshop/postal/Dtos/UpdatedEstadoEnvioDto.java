package com.workshop.postal.Dtos;

import com.workshop.postal.models.enums.EstadoEnvio;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedEstadoEnvioDto {
    private String numeroGuia;
    private EstadoEnvio ultimoEstadoEnvio;
}
