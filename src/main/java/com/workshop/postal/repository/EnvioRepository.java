package com.workshop.postal.repository;

import com.workshop.postal.models.Envio;
import com.workshop.postal.models.enums.EstadoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository<Envio,String> {
    Envio findByNumeroGuia(String numeroGuia);
    Boolean existsByNumeroGuia(String numeroGuia);
    List<Envio> findByEstadoEnvio(EstadoEnvio estadoEnvio);
}
