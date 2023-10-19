package com.workshop.postal.models;
import com.workshop.postal.models.enums.TipoEmpleado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@Entity

@PrimaryKeyJoinColumn(name="id")
public class Empleado extends Usuario{
    public Integer antiguedadEmpresa;
    public String rh;
    @Enumerated(EnumType.ORDINAL)
    private TipoEmpleado tipoEmpleado;
}
