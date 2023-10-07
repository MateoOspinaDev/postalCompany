package com.workshop.postal.models;
import com.workshop.postal.models.enums.TipoEmpleado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
