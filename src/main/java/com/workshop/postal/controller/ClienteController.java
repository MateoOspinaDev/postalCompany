package com.workshop.postal.controller;


import com.workshop.postal.Dtos.ClienteDto;
import com.workshop.postal.helpers.ClienteMapperHelper;
import com.workshop.postal.models.Cliente;
import com.workshop.postal.service.Interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    @Autowired
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();

        return clientes.stream()
                .map(ClienteMapperHelper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        return ResponseEntity.ok(ClienteMapperHelper.convertToDto(clienteService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(ClienteMapperHelper.convertToDto(clienteService.save(cliente)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(ClienteMapperHelper.convertToDto(clienteService.save(cliente)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.ok(true);
    }
}


