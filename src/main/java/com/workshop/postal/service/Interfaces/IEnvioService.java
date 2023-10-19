package com.workshop.postal.service.Interfaces;
import com.workshop.postal.dtos.*;
import com.workshop.postal.exceptions.BusinessException;
import com.workshop.postal.models.Envio;

import java.util.List;
public interface IEnvioService {
        List<Envio> getAllEnvios();
        EnvioRecibidoDto crearEnvio(EnvioDto request) throws BusinessException;
        Envio obtenerEnvioPorNumeroGuia(String numeroGuia);
        UpdatedEstadoEnvioDto actualizarEstadoEnvio(UpdateEstadoEnvioDto updateEstadoEnvioDto) throws BusinessException;
        List<Envio> filtrarEnviosPorEstado(GetEnvioPorEstadoDto getEnvioPorEstadoDto) throws BusinessException;
    }


