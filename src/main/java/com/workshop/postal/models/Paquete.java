package com.workshop.postal.models;

import com.workshop.postal.models.enums.TipoPaquete;
import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Paquete {

    @javax.persistence.Id
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

