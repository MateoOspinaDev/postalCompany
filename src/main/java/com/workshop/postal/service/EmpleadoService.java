package com.workshop.postal.service;

import com.workshop.postal.helpers.EnsureHelper;
import com.workshop.postal.models.Empleado;
import com.workshop.postal.repository.EmpleadoRepository;
import com.workshop.postal.service.Interfaces.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> findById(Long id) {
        EnsureHelper.ensureNotNull(id, "El id no puede ser nulo");
        return empleadoRepository.findById(id);
    }

    public Empleado save(Empleado empleado) {
        EnsureHelper.ensureNotNull(empleado, "El empleado no puede ser nulo");
        return empleadoRepository.save(empleado);
    }

    public void deleteById(Long id) {
        EnsureHelper.ensureNotNull(id, "El id no puede ser nulo");
        empleadoRepository.deleteById(id);
    }
}

