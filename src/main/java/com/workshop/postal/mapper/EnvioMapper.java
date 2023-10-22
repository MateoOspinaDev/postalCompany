package com.workshop.postal.mapper;

import com.workshop.postal.dtos.ClienteDto;
import com.workshop.postal.dtos.EnvioDto;
import com.workshop.postal.dtos.EnvioRecibidoDto;
import com.workshop.postal.dtos.GetEnvioDto;
import com.workshop.postal.models.Cliente;
import com.workshop.postal.models.Envio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnvioMapper {
    EnvioMapper MAPPER = Mappers.getMapper(EnvioMapper.class);

    EnvioDto envioToEnvioDto(Envio envio);
    EnvioRecibidoDto envioToEnvioRecibidoDto(Envio envio);
    GetEnvioDto envioToGetEnvioDto(Envio envio);
    Envio envioDtoToEnvio(EnvioDto envio);
}
