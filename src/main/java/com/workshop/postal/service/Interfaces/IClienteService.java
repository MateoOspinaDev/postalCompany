package com.workshop.postal.service.Interfaces;

import com.workshop.postal.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    Cliente update(Long id,Cliente cliente);
    void deleteById(Long id);
}
