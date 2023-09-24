package com.workshop.postal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.workshop.postal.models.enums.EstadoEnvio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "envios")
public class Envio {

    @Id
    @Column(name = "numero_guia", nullable = false, unique = true)
    @Setter(AccessLevel.PRIVATE)
    private String numeroGuia;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String direccionDestino;
    private String nombrePersonaReceptor;
    private String numeroCelularReceptor;
    private LocalDateTime horaEntrega;

    @Enumerated(EnumType.STRING)
    private EstadoEnvio estadoEnvio;
    private double valorEnvio;


    @OneToOne
    @JoinColumn(name = "paquete_id")
    private Paquete paquete;

    public Envio(String numeroGuia, Cliente cliente, String ciudadOrigen, String ciudadDestino, String direccionDestino, String nombrePersonaReceptor, String numeroCelularReceptor, LocalDateTime horaEntrega, EstadoEnvio estadoEnvio, double valorEnvio, Paquete paquete) {
        this.numeroGuia = numeroGuia;
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.direccionDestino = direccionDestino;
        this.nombrePersonaReceptor = nombrePersonaReceptor;
        this.numeroCelularReceptor = numeroCelularReceptor;
        this.horaEntrega = horaEntrega;
        this.estadoEnvio = estadoEnvio;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
    }
}
