package com.workshop.postal.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ClienteDto {
    private Long id;
    public String cedula;
    public String nombre;
    public String apellidos;
    public String celular;
    public String correoElectronico;
    public String direccionResidencia;
    public String ciudad;
    private List<EnvioRecibidoDto> envios = new ArrayList<>();
}
