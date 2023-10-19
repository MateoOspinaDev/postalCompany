package com.workshop.postal.controller;

import com.workshop.postal.dtos.*;
import com.workshop.postal.helpers.EnvioMapperHelper;
import com.workshop.postal.models.Envio;
import com.workshop.postal.models.enums.EstadoEnvio;
import com.workshop.postal.service.Interfaces.IEnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "OBTENER TODOS LOS ENVIOS",description = "Al hacer esta peticion se obtiene una lista con todos los envios que se han registrado hasta el momento en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de envíos."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping
    public ResponseEntity<List<GetEnvioDto>> getAllEnvios() {
        List<Envio> envios = envioService.getAllEnvios();
        return ResponseEntity.ok(envios.stream().map(EnvioMapperHelper::convertToDto).toList());
    }

    @Operation(summary = "BUSCAR Y OBTENER UN ENVIO POR NUMERO DE GUIA",description = "Al hacer esta peticion podemos obtener la informacion de un envio especifico pasando como parametro su numero de guia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información del envío."),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping("/{numeroGuia}")
    public ResponseEntity<GetEnvioDto> getEnvioByNumeroGuia(@PathVariable String numeroGuia) {
        return ResponseEntity.ok(EnvioMapperHelper.convertToDto(envioService.obtenerEnvioPorNumeroGuia(numeroGuia)));
    }

    @Operation(summary = "OBTENER UNA LISTA DE LOS ENVIOS SEGUN SEA SU ESTADO",description = "Se debe pasar como parametro el estado de envio y la cedula de un empleado valido para poder obtener la informacion de los envios existentes en el sistema, que cumplan el estado de envio indicado en el parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de envíos segun estado ingresado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping("/filtrarPorEstado")
    public ResponseEntity<List<GetEnvioDto>> filtrarEnviosPorEstado(@RequestParam EstadoEnvio estadoEnvio, @RequestParam String cedulaEmpleado) {
        List<Envio> envios = envioService
                .filtrarEnviosPorEstado(GetEnvioPorEstadoDto.builder().estadoEnvio(estadoEnvio).cedulaEmpleado(cedulaEmpleado).build());

        return ResponseEntity.ok(envios.stream().map(EnvioMapperHelper::convertToDto).toList());
    }

@Operation(summary = "CREAR UN ENVIO",description = "Se debe pasar como parametro un objeto de tipo EnvioDto con todos sus datos, para poder registrar un nuevo envio en el sistema")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Envio creado con exito"),
        @ApiResponse(responseCode = "400", description = "No se pudo crear el envio con los datos ingresados"),
        @ApiResponse(responseCode = "404", description = "Envio no encontrado"),
        @ApiResponse(responseCode = "403", description = "Operación prohibida"),
        @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operación"),
        @ApiResponse(responseCode = "500", description = "Error de conexion")
})
    @PostMapping
    public ResponseEntity<EnvioRecibidoDto> createEnvio(@RequestBody EnvioDto envioDto) {
        return ResponseEntity.ok(envioService.crearEnvio(envioDto));
    }
    @Operation(summary = "ACTUALIZAR UN ENVIO",description = "Se debe pasar como parametro un objeto de tipo UpdateEnvioDto con todos sus datos, para poder actualizar un envio en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El envío se ha actualizado correctamente."),
            @ApiResponse(responseCode = "400", description = "Los datos proporcionados no son válidos."),
            @ApiResponse(responseCode = "404", description = "El Envio no se a encontrado"),
            @ApiResponse(responseCode = "403", description = "Operación prohibida, No se tiene permiso para actualizar el envío."),
            @ApiResponse(responseCode = "500", description = "Error de conexion")
    })
    @PutMapping()
    public ResponseEntity<UpdatedEstadoEnvioDto> updateEstadoEnvio(@RequestBody UpdateEstadoEnvioDto updateEstadoEnvioDto) {
        return ResponseEntity.ok(envioService.actualizarEstadoEnvio(updateEstadoEnvioDto));
    }

}
