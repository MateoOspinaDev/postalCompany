package com.workshop.postal.controller;

import com.workshop.postal.dtos.EmpleadoDto;
import com.workshop.postal.models.Empleado;
import com.workshop.postal.service.Interfaces.IEmpleadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmpleadoController(IEmpleadoService empleadoService, ModelMapper modelMapper) {
        this.empleadoService = empleadoService;
        this.modelMapper = modelMapper;
    }


    @ApiOperation(value = "OBTENER TODOS LOS EMPLEADOS", notes = "Busca en la base de datos y nos muestra la informacion de todos los empleados registrados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de Empleados."),
           @ApiResponse(code = 500, message = "Error interno del servidor.")
   })
    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.findAll();
    }

    @ApiOperation(value ="OBTENER EMPLEADO POR ID",notes = "Busca el empleado con el ID dado el cual debemos pasar como parametro, al encontrarlo nos muestra toda su informacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empleado encontrado."),
            @ApiResponse(code = 404, message = "Empleado no encontrado."),
            @ApiResponse(code = 500, message = "Error interno del servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> getEmpleadoById(@PathVariable Long id) {
        Empleado empleado = empleadoService.findById(id);
        return ResponseEntity.ok(modelMapper.map(empleado, EmpleadoDto.class));
    }
    @ApiOperation(value = "CREAR O AÑADIR UN EMPLEADO", notes = "Esta funcion se usa para añadir un nuevo empleado al sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empleado creado con exito"),
            @ApiResponse(code = 400, message = "No se pudo crear el empleado con los datos ingresados"),
            @ApiResponse(code = 404, message = "Empleado no encontrado"),
            @ApiResponse(code = 403, message = "Operacion prohibida"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })

    @PostMapping
    public EmpleadoDto createEmpleado(@RequestBody Empleado empleado) {
        return modelMapper.map(empleadoService.save(empleado), EmpleadoDto.class);
    }
    @ApiOperation(
            value = "ACTUALIZAR EMPLEADO POR ID",
            notes = "Busca el empleado con el ID dado y si existe permite actualizarlo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empleado actualizado con éxito."),
            @ApiResponse(code = 400, message = " Datos  mal ingresado/s"),
            @ApiResponse(code = 404, message = "Empleado no encontrado."),
            @ApiResponse(code = 500, message = "Error interno del servidor.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        return ResponseEntity.ok(modelMapper.map(empleadoService.save(empleado), EmpleadoDto.class));
    }
    @ApiOperation(
           value = "BORRAR EMPLEADO POR ID", notes = "Busca el empleado con el ID dado y si existe lo borra")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empleado borrado con éxito."),
            @ApiResponse(code = 404, message = "Empleado no encontrado."),
            @ApiResponse(code = 500, message = "Error interno del servidor.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteById(id);
        return ResponseEntity.ok(true);
    }
}

