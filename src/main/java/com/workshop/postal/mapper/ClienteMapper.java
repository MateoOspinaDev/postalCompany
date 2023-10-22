package com.workshop.postal.mapper;

import com.workshop.postal.dtos.ClienteDto;
import com.workshop.postal.models.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {
    ClienteMapper MAPPER = Mappers.getMapper(ClienteMapper.class);
    Cliente clienteDtoToCliente(Cliente clienteDto);
    @Mapping(target = "envios", source = "envios")
    ClienteDto clienteToClienteDto(Cliente cliente);
}
