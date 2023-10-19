package com.workshop.postal.helpers;

import com.workshop.postal.dtos.ClienteDto;
import com.workshop.postal.dtos.EnvioRecibidoDto;
import com.workshop.postal.models.Cliente;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class ClienteMapperHelper {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    public static ClienteDto convertToDto(Cliente cliente) {
        ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);

        clienteDto.setEnvios(
                cliente.getEnvios().stream()
                        .map(envio -> modelMapper.map(envio, EnvioRecibidoDto.class))
                        .collect(Collectors.toList())
        );

        return clienteDto;
    }
}
