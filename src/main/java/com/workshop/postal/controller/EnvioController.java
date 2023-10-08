package com.workshop.postal.controller;

import com.workshop.postal.dtos.*;
import com.workshop.postal.helpers.EnvioMapperHelper;
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
    public ResponseEntity<List<GetEnvioDto>> getAllEnvios() {
        List<Envio> envios = envioService.getAllEnvios();
        return ResponseEntity.ok(envios.stream().map(EnvioMapperHelper::convertToDto).toList());
    }

    @GetMapping("/{numeroGuia}")
    public ResponseEntity<GetEnvioDto> getEnvioByNumeroGuia(@PathVariable String numeroGuia) {
        return ResponseEntity.ok(EnvioMapperHelper.convertToDto(envioService.obtenerEnvioPorNumeroGuia(numeroGuia)));
    }

    @GetMapping("/filtrarPorEstado")
    public ResponseEntity<List<GetEnvioDto>> filtrarEnviosPorEstado(@RequestParam EstadoEnvio estadoEnvio, @RequestParam String cedulaEmpleado) {
        List<Envio> envios = envioService
                .filtrarEnviosPorEstado(GetEnvioPorEstadoDto.builder().estadoEnvio(estadoEnvio).cedulaEmpleado(cedulaEmpleado).build());

        return ResponseEntity.ok(envios.stream().map(EnvioMapperHelper::convertToDto).toList());
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
