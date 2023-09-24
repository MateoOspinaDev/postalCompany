package com.workshop.postal.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public String cedula;
    public String nombre;
    public String apellidos;
    public String celular;
    public String correoElectronico;
    public String direccionResidencia;
    public String ciudad;
}
