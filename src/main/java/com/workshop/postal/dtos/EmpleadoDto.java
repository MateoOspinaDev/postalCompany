package com.workshop.postal.dtos;

import com.workshop.postal.models.enums.TipoEmpleado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class EmpleadoDto {
    private Long id;
    public String cedula;
    public String nombre;
    public String apellidos;
    public String celular;
    public String correoElectronico;
    public String direccionResidencia;
    public String ciudad;
    public Integer antiguedadEmpresa;
    public String rh;
    @Enumerated(EnumType.ORDINAL)
    private TipoEmpleado tipoEmpleado;
}
