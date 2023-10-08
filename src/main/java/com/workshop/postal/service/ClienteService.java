package com.workshop.postal.service;


import com.workshop.postal.helpers.EnsureHelper;
import com.workshop.postal.models.Cliente;
import com.workshop.postal.repository.ClienteRepository;
import com.workshop.postal.service.Interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        EnsureHelper.ensureNotNull(id, "El id no puede ser nulo");
        return clienteRepository.findById(id).orElseThrow();
    }

    public Cliente save(Cliente cliente) {
        EnsureHelper.ensureNotNull(cliente, "El cliente no puede ser nulo");
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id,Cliente cliente) {
        EnsureHelper.ensureNotNull(id, "El id no puede ser nulo");
        EnsureHelper.ensureNotNull(cliente, "El cliente no puede ser nulo");

        boolean exists = clienteRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("El cliente con id " + id + " no existe");
        }

        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        EnsureHelper.ensureNotNull(id, "El id no puede ser nulo");

        boolean exists = clienteRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("El cliente con id " + id + " no existe");
        }

        clienteRepository.deleteById(id);
    }
}


