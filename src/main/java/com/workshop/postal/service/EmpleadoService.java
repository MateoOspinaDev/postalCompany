package com.workshop.postal.service;

import com.workshop.postal.exceptions.BusinessException;
import com.workshop.postal.helpers.EnsureHelper;
import com.workshop.postal.models.Empleado;
import com.workshop.postal.repository.EmpleadoRepository;
import com.workshop.postal.service.Interfaces.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Empleado findById(Long id) {
        EnsureHelper.ensureNotNull(id, "El id no puede ser nulo");

        if (empleadoRepository.findById(id).isEmpty()) {
            throw new BusinessException("El empleado no existe");
        }

        return empleadoRepository.findById(id).orElseThrow();
    }

    public Empleado save(Empleado empleado) {
        EnsureHelper.ensureNotNull(empleado, "El empleado no puede ser nulo");
        return empleadoRepository.save(empleado);
    }

    public Empleado update(Long id, Empleado empleado) {
        EnsureHelper.ensureNotNull(id, "El id no puede ser nulo");
        EnsureHelper.ensureNotNull(empleado, "El empleado no puede ser nulo");

        if (empleadoRepository.findById(id).isEmpty()) {
            throw new BusinessException("El empleado no existe");
        }

        return empleadoRepository.save(empleado);
    }

    public void deleteById(Long id) {
        EnsureHelper.ensureNotNull(id, "El id no puede ser nulo");

        if (empleadoRepository.findById(id).isEmpty()) {
            throw new BusinessException("El empleado no existe");
        }

        empleadoRepository.deleteById(id);
    }
}

