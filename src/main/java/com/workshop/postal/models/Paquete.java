package com.workshop.postal.models;

import com.workshop.postal.models.enums.TipoPaquete;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Paquete {

    @Id
    @Setter(AccessLevel.PRIVATE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    private double valorDeclarado;

    @Enumerated(EnumType.ORDINAL)
    private TipoPaquete tipoPaquete;

    public Paquete(double peso, double valorDeclarado, TipoPaquete tipoPaquete) {
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
        this.tipoPaquete = tipoPaquete;
    }


}

