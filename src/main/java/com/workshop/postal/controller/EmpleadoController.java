package com.workshop.postal.controller;

import com.workshop.postal.dtos.EmpleadoDto;
import com.workshop.postal.mapper.EmpleadoMapper;
import com.workshop.postal.models.Empleado;
import com.workshop.postal.service.Interfaces.IEmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @Operation(
            summary = "OBTENER TODOS LOS EMPLEADOS",
            description = "Busca en la base de datos y nos muestra la informacion de todos los empleados registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Empleados."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.findAll();
    }
    @Operation(
            summary = "OBTENER EMPLEADO POR ID",
            description = "Busca el empleado con el ID dado el cual debemos pasar como parametro, al encontrarlo nos muestra toda su informacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado."),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> getEmpleadoById(@PathVariable Long id) {
        Empleado empleado = empleadoService.findById(id);
        return ResponseEntity.ok(EmpleadoMapper.MAPPER.empleadoToEmpleadoDto(empleado));
    }
    @Operation(
            summary = "CREAR O AÑADIR UN EMPLEADO",
            description = "Esta funcion se usa para añadir un nuevo empleado al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado creado con exito"),
            @ApiResponse(responseCode = "400", description = "No se pudo crear el empleado con los datos ingresados"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado"),
            @ApiResponse(responseCode = "403", description = "Operacion prohibida"),
            @ApiResponse(responseCode = "500", description = "Error de conexion")
    })

    @PostMapping
    public EmpleadoDto createEmpleado(@RequestBody Empleado empleado) {
        return EmpleadoMapper.MAPPER.empleadoToEmpleadoDto(empleadoService.save(empleado));
    }
    @Operation(
            summary = "ACTUALIZAR EMPLEADO POR ID",
            description = "Busca el empleado con el ID dado y si existe permite actualizarlo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado actualizado con éxito."),
            @ApiResponse(responseCode = "400", description = " Datos  mal ingresado/s"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        return ResponseEntity.ok(EmpleadoMapper.MAPPER.empleadoToEmpleadoDto(empleadoService.save(empleado)));
    }
    @Operation(
            summary = "BORRAR EMPLEADO POR ID",
            description = "Busca el empleado con el ID dado y si existe lo borra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado borrado con éxito."),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteById(id);
        return ResponseEntity.ok(true);
    }
}

