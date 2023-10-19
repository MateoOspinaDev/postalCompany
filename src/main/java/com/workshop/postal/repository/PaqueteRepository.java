package com.workshop.postal.repository;

import com.workshop.postal.models.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete,Long> {
}
