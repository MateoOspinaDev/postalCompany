package com.workshop.postal.controller;

import com.workshop.postal.Dtos.*;
import com.workshop.postal.models.Envio;
import com.workshop.postal.models.enums.EstadoEnvio;
import com.workshop.postal.service.Interfaces.IEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final IEnvioService envioService;

    @Autowired
    public EnvioController(IEnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public ResponseEntity<List<Envio>> getAllEnvios() {
        return ResponseEntity.ok(envioService.getAllEnvios());
    }

    @GetMapping("/{numeroGuia}")
    public ResponseEntity<Envio> getEnvioByNumeroGuia(@PathVariable String numeroGuia) {
        return ResponseEntity.ok(envioService.obtenerEnvioPorNumeroGuia(numeroGuia));
    }

    @GetMapping("/filtrarPorEstado")
    public ResponseEntity<List<Envio>> filtrarEnviosPorEstado(@RequestParam EstadoEnvio estadoEnvio, @RequestParam String cedulaEmpleado) {
        return ResponseEntity.ok(envioService
                .filtrarEnviosPorEstado(GetEnvioPorEstadoDto.builder().estadoEnvio(estadoEnvio).cedulaEmpleado(cedulaEmpleado).build()));
    }


    @PostMapping
    public ResponseEntity<EnvioRecibidoDto> createEnvio(@RequestBody EnvioDto envioDto) {
        return ResponseEntity.ok(envioService.crearEnvio(envioDto));
    }

    @PutMapping()
    public ResponseEntity<UpdatedEstadoEnvioDto> updateEstadoEnvio(@RequestBody UpdateEstadoEnvioDto updateEstadoEnvioDto) {
        return ResponseEntity.ok(envioService.actualizarEstadoEnvio(updateEstadoEnvioDto));
    }

}
