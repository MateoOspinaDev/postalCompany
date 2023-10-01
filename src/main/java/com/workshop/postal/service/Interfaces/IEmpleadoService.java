package com.workshop.postal.service.Interfaces;

import com.workshop.postal.models.Empleado;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {
    List<Empleado> findAll();
    Empleado findById(Long id);
    Empleado update(Long id, Empleado empleado);
    Empleado save(Empleado empleado);
    void deleteById(Long id);
}

