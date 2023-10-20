package com.workshop.postal.controller;


import com.workshop.postal.dtos.ClienteDto;
import com.workshop.postal.helpers.ClienteMapperHelper;
import com.workshop.postal.models.Cliente;
import com.workshop.postal.service.Interfaces.IClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(
                summary = "OBTENER TODOS LOS CLIENTES",
                description = "Busca en la base de datos y nos muestra la informacion de todos los clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping
    @SecurityRequirement(name = "bearer")
    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();

        return clientes.stream()
                .map(ClienteMapperHelper::convertToDto)
                .collect(Collectors.toList());
    }

   @Operation(
                summary = "OBTENER CLIENTE POR ID",
                description = "Busca el cliente con el ID dado el cual debemos pasar como parametro, al encontrarlo nos muestra toda su informacion"
   )
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Cliente encontrado."),
           @ApiResponse(responseCode = "404", description = "Cliente no encontrado."),
           @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
   })
    @GetMapping("/{id}")
   @SecurityRequirement(name = "bearer")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        return ResponseEntity.ok(ClienteMapperHelper.convertToDto(clienteService.findById(id)));
    }

    @Operation(
                summary = "CREAR O AÑADIR UN CLIENTE",
                description = "Esta funcion se usa para añadir un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado con exito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "400", description = " Datos  mal ingresado/s"),
            @ApiResponse(responseCode = "401", description = " No esta autorizado para realizar esta operación"),
            @ApiResponse(responseCode = "403", description = " Operacion prohibida"),
            @ApiResponse(responseCode = "500", description = "Error de conexion")
    })
    @PostMapping
    @SecurityRequirement(name = "bearer")
    public ResponseEntity<ClienteDto> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(ClienteMapperHelper.convertToDto(clienteService.save(cliente)));
    }

   @Operation(
              summary = "ACTUALIZAR CLIENTE POR ID",
              description = "Busca el cliente con el ID dado y si existe permite actualizarlo")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito."),
           @ApiResponse(responseCode = "400", description = " Datos  mal ingresado/s"),
           @ApiResponse(responseCode = "404", description = "Cliente no encontrado."),
           @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
   })
    @PutMapping("/{id}")
   @SecurityRequirement(name = "bearer")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(ClienteMapperHelper.convertToDto(clienteService.save(cliente)));
    }

   @Operation(
                summary = "BORRAR CLIENTE POR ID",
                description = "Busca el cliente con el ID dado y si existe lo borra")

   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Cliente borrado con éxito."),
           @ApiResponse(responseCode = "404", description = "Cliente no encontrado."),
           @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
   })
    @DeleteMapping("/{id}")
   @SecurityRequirement(name = "bearer")
    public ResponseEntity<Boolean> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.ok(true);
    }
}


