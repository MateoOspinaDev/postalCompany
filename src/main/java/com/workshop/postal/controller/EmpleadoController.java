package com.workshop.postal.controller;

import com.workshop.postal.dtos.EmpleadoDto;
import com.workshop.postal.models.Empleado;
import com.workshop.postal.service.Interfaces.IEmpleadoService;
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

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> getEmpleadoById(@PathVariable Long id) {
        Empleado empleado = empleadoService.findById(id);
        return ResponseEntity.ok(modelMapper.map(empleado, EmpleadoDto.class));
    }

    @PostMapping
    public EmpleadoDto createEmpleado(@RequestBody Empleado empleado) {
        return modelMapper.map(empleadoService.save(empleado), EmpleadoDto.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        return ResponseEntity.ok(modelMapper.map(empleadoService.save(empleado), EmpleadoDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteById(id);
        return ResponseEntity.ok(true);
    }
}

