package com.workshop.postal.controller;

import com.workshop.postal.dtos.*;
import com.workshop.postal.helpers.EnvioMapperHelper;
import com.workshop.postal.models.Envio;
import com.workshop.postal.models.enums.EstadoEnvio;
import com.workshop.postal.service.Interfaces.IEnvioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "OBTENER TODOS LOS ENVIOS",notes = "Al hacer esta peticion se obtiene una lista con todos los envios que se han registrado hasta el momento en el sistema")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Lista de envíos."),
            @ApiResponse(code = 500, message = "Error interno del servidor.")
    })
    @GetMapping
    public ResponseEntity<List<GetEnvioDto>> getAllEnvios() {
        List<Envio> envios = envioService.getAllEnvios();
        return ResponseEntity.ok(envios.stream().map(EnvioMapperHelper::convertToDto).toList());
    }

    @ApiOperation(value = "BUSCAR Y OBTENER UN ENVIO POR NUMERO DE GUIA",notes = "Al hacer esta peticion podemos obtener la informacion de un envio especifico pasando como parametro su numero de guia")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Información del envío."),
            @ApiResponse(code = 404, message = "Envío no encontrado."),
            @ApiResponse(code = 500, message = "Error interno del servidor.")
    })
    @GetMapping("/{numeroGuia}")
    public ResponseEntity<GetEnvioDto> getEnvioByNumeroGuia(@PathVariable String numeroGuia) {
        return ResponseEntity.ok(EnvioMapperHelper.convertToDto(envioService.obtenerEnvioPorNumeroGuia(numeroGuia)));
    }

   @ApiOperation(value = "OBTENER UNA LISTA DE LOS ENVIOS SEGUN SEA SU ESTADO",notes = "Se debe pasar como parametro el estado de envio y la cedula de un empleado valido para poder obtener la informacion de los envios existentes en el sistema, que cumplan el estado de envio indicado en el parametro")
    @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Lista de envíos segun estado ingresado."),
          @ApiResponse(code = 500, message = "Error interno del servidor.")
    })
    @GetMapping("/filtrarPorEstado")
    public ResponseEntity<List<GetEnvioDto>> filtrarEnviosPorEstado(@RequestParam EstadoEnvio estadoEnvio, @RequestParam String cedulaEmpleado) {
        List<Envio> envios = envioService
                .filtrarEnviosPorEstado(GetEnvioPorEstadoDto.builder().estadoEnvio(estadoEnvio).cedulaEmpleado(cedulaEmpleado).build());

        return ResponseEntity.ok(envios.stream().map(EnvioMapperHelper::convertToDto).toList());
    }

@ApiOperation(value = "CREAR UN ENVIO",notes = "Se debe pasar como parametro un objeto de tipo EnvioDto con todos sus datos, para poder registrar un nuevo envio en el sistema")
@ApiResponses(value = {
        @ApiResponse(code = 201, message = "Envio creado con exito"),
        @ApiResponse(code = 400, message = "No se pudo crear el envio con los datos ingresados"),
       @ApiResponse(code = 404, message = "Envio no encontrado"),
        @ApiResponse(code = 403, message = "Operación prohibida"),
        @ApiResponse(code = 401, message = "No esta autorizado para realizar esta operación"),
        @ApiResponse(code = 500, message = "Error de conexion")
})
    @PostMapping
    public ResponseEntity<EnvioRecibidoDto> createEnvio(@RequestBody EnvioDto envioDto) {
        return ResponseEntity.ok(envioService.crearEnvio(envioDto));
    }
    @ApiOperation(value = "ACTUALIZAR UN ENVIO",notes = "Se debe pasar como parametro un objeto de tipo UpdateEnvioDto con todos sus datos, para poder actualizar un envio en el sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El envío se ha actualizado correctamente."),
            @ApiResponse(code = 400, message = "Los datos proporcionados no son válidos."),
            @ApiResponse(code = 404, message = "El Envio no se a encontrado"),
            @ApiResponse(code = 403, message = "Operación prohibida, No se tiene permiso para actualizar el envío."),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @PutMapping()
    public ResponseEntity<UpdatedEstadoEnvioDto> updateEstadoEnvio(@RequestBody UpdateEstadoEnvioDto updateEstadoEnvioDto) {
        return ResponseEntity.ok(envioService.actualizarEstadoEnvio(updateEstadoEnvioDto));
    }

}
