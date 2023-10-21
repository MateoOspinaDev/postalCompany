package com.workshop.postal.repository;

import com.workshop.postal.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    void deleteById(Long id);
    Optional<Cliente> findById(Long id);
    Cliente findByCedula(String cedulaCliente);
}
