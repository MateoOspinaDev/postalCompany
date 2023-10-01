package com.workshop.postal.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Usuario {
    @Id
    @Setter(AccessLevel.PRIVATE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Setter(AccessLevel.PRIVATE)
    public String cedula;
    public String nombre;
    public String apellidos;
    public String celular;
    public String correoElectronico;
    public String direccionResidencia;
    public String ciudad;
}
