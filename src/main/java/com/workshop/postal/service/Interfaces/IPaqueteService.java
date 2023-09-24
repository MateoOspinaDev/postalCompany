package com.workshop.postal.service.Interfaces;
import com.workshop.postal.models.Paquete;

import java.util.List;
import java.util.Optional;
public interface IPaqueteService {
        List<Paquete> getAllPaquetes();
        Optional<Paquete> getPaqueteById(Long id);
        Paquete createPaquete(Paquete paquete);
        Paquete updatePaquete(Long id, Paquete paqueteActualizado);
        void deletePaquete(Long id);
    }


