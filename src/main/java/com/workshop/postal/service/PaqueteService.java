package com.workshop.postal.service;

import com.workshop.postal.models.Paquete;
import com.workshop.postal.repository.PaqueteRepository;
import com.workshop.postal.service.Interfaces.IPaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PaqueteService implements IPaqueteService {

    private final PaqueteRepository paqueteRepository;

    @Autowired
    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }

    @Override
    public List<Paquete> getAllPaquetes() {
        return paqueteRepository.findAll();
    }

    @Override
    public Optional<Paquete> getPaqueteById(Long id) {
        return paqueteRepository.findById(id);
    }

    @Override
    public Paquete createPaquete(Paquete paquete) {
        return paqueteRepository.save(paquete);
    }

    @Override
    public Paquete updatePaquete(Long id, Paquete paqueteActualizado) {
        paqueteActualizado.setId(id);
        return paqueteRepository.save(paqueteActualizado);
    }

    @Override
    public void deletePaquete(Long id) {
        paqueteRepository.deleteById(id);
    }
}
