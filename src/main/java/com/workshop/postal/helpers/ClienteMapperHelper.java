package com.workshop.postal.helpers;

import com.workshop.postal.dtos.ClienteDto;
import com.workshop.postal.mapper.ClienteMapper;
import com.workshop.postal.mapper.EnvioMapper;
import com.workshop.postal.models.Cliente;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapperHelper {
private static final ClienteMapper CLIENTE_MAPPER = Mappers.getMapper(ClienteMapper.class);

    public static ClienteDto convertToDto(Cliente cliente) {
        ClienteDto clienteDto = CLIENTE_MAPPER.clienteToClienteDto(cliente);

        clienteDto.setEnvios(
                cliente.getEnvios().stream()
                        .map(EnvioMapper.MAPPER::envioToEnvioRecibidoDto)
                        .collect(Collectors.toList())
        );

        return clienteDto;
    }
}
