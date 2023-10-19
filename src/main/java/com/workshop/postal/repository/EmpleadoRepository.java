package com.workshop.postal.repository;

import com.workshop.postal.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findById(Long id);
    void deleteById(Long id);
    Empleado findByCedula(String cedulaEmpleado);
}
