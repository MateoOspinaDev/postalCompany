package com.workshop.postal.helpers;

import com.workshop.postal.dtos.GetEnvioDto;
import com.workshop.postal.models.Envio;
import org.modelmapper.ModelMapper;

public class EnvioMapperHelper {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    public static GetEnvioDto convertToDto(Envio envio) {
        GetEnvioDto getEnvioDto = modelMapper.map(envio, GetEnvioDto.class);
        getEnvioDto.setCedulaCliente(envio.getCliente().getCedula());
        getEnvioDto.setValorDeclaradoPaquete(envio.getPaquete().getValorDeclarado());
        getEnvioDto.setNombreRecibe(envio.getCliente().getNombre());
        getEnvioDto.setPeso(envio.getPaquete().getPeso());
        getEnvioDto.setEstadoEnvio(envio.getEstadoEnvio().toString());
        return getEnvioDto;
    }
}
