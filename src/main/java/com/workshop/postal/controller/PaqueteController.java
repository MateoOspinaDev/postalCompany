package com.workshop.postal.controller;
import com.workshop.postal.models.Paquete;
import com.workshop.postal.service.Interfaces.IPaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/paquetes")
    public class PaqueteController {

        private final IPaqueteService paqueteService;

        @Autowired
        public PaqueteController(IPaqueteService paqueteService) {
            this.paqueteService = paqueteService;
        }

        @GetMapping("/")
        public List<Paquete> getAllPaquetes() {
            return paqueteService.getAllPaquetes();
        }

        @GetMapping("/{id}")
        public Optional<Paquete> getPaqueteById(@PathVariable Long id) {
            return paqueteService.getPaqueteById(id);
        }

        @PostMapping("/")
        public Paquete createPaquete(@RequestBody Paquete paquete) {
            return paqueteService.createPaquete(paquete);
        }

        @PutMapping("/{id}")
        public Paquete updatePaquete(@PathVariable Long id, @RequestBody Paquete paqueteActualizado) {
            return paqueteService.updatePaquete(id, paqueteActualizado);
        }

        @DeleteMapping("/{id}")
        public void deletePaquete(@PathVariable Long id) {
            paqueteService.deletePaquete(id);
        }
    }


