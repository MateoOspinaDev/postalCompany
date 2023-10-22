package com.workshop.postal.mapper;

import com.workshop.postal.dtos.ClienteDto;
import com.workshop.postal.dtos.EmpleadoDto;
import com.workshop.postal.models.Cliente;
import com.workshop.postal.models.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmpleadoMapper {
    EmpleadoMapper MAPPER = Mappers.getMapper(EmpleadoMapper.class);
    Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);
    EmpleadoDto empleadoToEmpleadoDto(Empleado empleado);
}
