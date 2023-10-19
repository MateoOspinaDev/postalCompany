package com.workshop.postal.controller;


import com.workshop.postal.dtos.ClienteDto;
import com.workshop.postal.helpers.ClienteMapperHelper;
import com.workshop.postal.models.Cliente;
import com.workshop.postal.service.Interfaces.IClienteService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


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

    @ApiOperation(value = "Obtener todos los clientes", notes = "Nos da una lista que incluye todos los clientes registrados en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de clientes"),
            @ApiResponse(code = 500, message = "Error interno del servidor.")
    })

    @GetMapping
    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();

        return clientes.stream()
                .map(ClienteMapperHelper::convertToDto)
                .collect(Collectors.toList());
    }



    @ApiOperation(value = "Obtener Cliente Por ID", notes = "Busca el cliente con el ID dado el cual debemos pasar como parametro, al encontrarlo nos muestra toda su informacion.")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Cliente encontrado."),
           @ApiResponse(code = 404, message = "Cliente no encontrado."),
           @ApiResponse(code = 500, message = "Error interno del servidor.")
   })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        return ResponseEntity.ok(ClienteMapperHelper.convertToDto(clienteService.findById(id)));
    }


    @ApiOperation(value = "Crear o añadir a un cliente", notes = "Esta funcion se usa para añadir un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente creado con exito"),
            @ApiResponse(code = 404, message = "Cliente no encontrado"),
            @ApiResponse(code = 400, message = " Datos  mal ingresado/s"),
            @ApiResponse(code = 401, message = " No esta autorizado para realizar esta operación"),
            @ApiResponse(code = 403, message = " Operacion prohibida"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(ClienteMapperHelper.convertToDto(clienteService.save(cliente)));
    }


   @ApiOperation(value = "ACTUALIZAR CLIENTE POR ID", notes = "Busca el cliente con el ID dado y si existe permite actualizarlo")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Cliente actualizado con éxito."),
           @ApiResponse(code = 400, message = " Datos  mal ingresado/s"),
           @ApiResponse(code = 404, message = "Cliente no encontrado."),
           @ApiResponse(code = 500, message = "Error interno del servidor.")
   })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(ClienteMapperHelper.convertToDto(clienteService.save(cliente)));
    }


    @ApiOperation(value = "BORRAR CLIENTE POR ID",notes = "Busca el cliente con el ID dado y si existe lo borra")
   @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Cliente borrado con éxito."),
          @ApiResponse(code = 404, message = "Cliente no encontrado."),
           @ApiResponse(code = 500, message = "Error interno del servidor.")
   })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.ok(true);
    }
}


