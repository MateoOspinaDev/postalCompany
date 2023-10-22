package com.workshop.postal.helpers;

import com.workshop.postal.dtos.GetEnvioDto;
import com.workshop.postal.mapper.EnvioMapper;
import com.workshop.postal.models.Envio;
import org.mapstruct.factory.Mappers;

public class EnvioMapperHelper {
    private static final EnvioMapper ENVIO_MAPPER = Mappers.getMapper(EnvioMapper.class);

    public static GetEnvioDto convertToDto(Envio envio) {
        GetEnvioDto getEnvioDto = ENVIO_MAPPER.envioToGetEnvioDto(envio);
        getEnvioDto.setCedulaCliente(envio.getCliente().getCedula());
        getEnvioDto.setValorDeclaradoPaquete(envio.getPaquete().getValorDeclarado());
        getEnvioDto.setNombreRecibe(envio.getCliente().getNombre());
        getEnvioDto.setPeso(envio.getPaquete().getPeso());
        getEnvioDto.setEstadoEnvio(envio.getEstadoEnvio().toString());
        return getEnvioDto;
    }
}
