package com.workshop.postal.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.workshop.postal.dtos.UpdateEstadoEnvioDto;
import com.workshop.postal.dtos.UpdatedEstadoEnvioDto;
import com.workshop.postal.exceptions.BusinessException;
import com.workshop.postal.models.Empleado;
import com.workshop.postal.models.Envio;
import com.workshop.postal.models.enums.EstadoEnvio;

import com.workshop.postal.models.enums.TipoEmpleado;
import com.workshop.postal.models.enums.TipoPaquete;
import com.workshop.postal.repository.ClienteRepository;
import com.workshop.postal.repository.EmpleadoRepository;
import com.workshop.postal.repository.EnvioRepository;
import com.workshop.postal.service.EnvioService;
import com.workshop.postal.service.PaqueteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


public class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private PaqueteService paqueteService;

    @InjectMocks
    private EnvioService envioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        envioService = new EnvioService(envioRepository, null, empleadoRepository, null);
    }

    @Test
    public void testGetAllEnvios() {
        // Caso de prueba 1: La lista de envíos no está vacía
        List<Envio> envios = new ArrayList<>();
        envios.add(new Envio());
        when(envioRepository.findAll()).thenReturn(envios);

        List<Envio> result = envioService.getAllEnvios();

        assertNotNull(result);
        assertEquals(envios.size(), result.size());

        // Caso de prueba 2: La lista de envíos está vacía
        when(envioRepository.findAll()).thenReturn(new ArrayList<>());

        List<Envio> result2 = envioService.getAllEnvios();

        assertNotNull(result2);
        assertEquals(0, result2.size());
    }



    @Test
    public void testGenerarNumeroGuia() {
        String numeroGuia1 = envioService.generarNumeroGuia();
        String numeroGuia2 = envioService.generarNumeroGuia();

        assertNotNull(numeroGuia1);
        assertNotNull(numeroGuia2);
        assertNotEquals(numeroGuia1, numeroGuia2);
    }

    @Test
    public void testCalcularTipoPaquete() {
        double pesoLiviano = 1.5;
        double pesoMediano = 3.5;
        double pesoGrande = 5.5;

        TipoPaquete tipoLiviano = envioService.calcularTipoPaquete(pesoLiviano);
        TipoPaquete tipoMediano = envioService.calcularTipoPaquete(pesoMediano);
        TipoPaquete tipoGrande = envioService.calcularTipoPaquete(pesoGrande);

        assertEquals(TipoPaquete.LIVIANO, tipoLiviano);
        assertEquals(TipoPaquete.MEDIANO, tipoMediano);
        assertEquals(TipoPaquete.GRANDE, tipoGrande);
    }

    @Test
    public void testCalcularValorEnvio() {
        TipoPaquete tipoLiviano = TipoPaquete.LIVIANO;
        TipoPaquete tipoMediano = TipoPaquete.MEDIANO;
        TipoPaquete tipoGrande = TipoPaquete.GRANDE;

        double valorLiviano = envioService.calcularValorEnvio(tipoLiviano);
        double valorMediano = envioService.calcularValorEnvio(tipoMediano);
        double valorGrande = envioService.calcularValorEnvio(tipoGrande);

        assertEquals(30000, valorLiviano);
        assertEquals(40000, valorMediano);
        assertEquals(50000, valorGrande);
    }

    @Test
    public void testPuedeCambiarseEstado() {
        // Caso 1: Cambio de RECIBIDO a EN_RUTA que es el caso que debe aceptar segun la guia del trabajo
        assertTrue(envioService.puedeCambiarseEstado(EstadoEnvio.RECIBIDO, EstadoEnvio.EN_RUTA));
        // Caso 2: Cambio de EN_RUTA a ENTREGADO que es el caso que debe aceptar segun la guia del trabajo
        assertTrue(envioService.puedeCambiarseEstado(EstadoEnvio.EN_RUTA, EstadoEnvio.ENTREGADO));
        // estos otros no los debe aceptar segun la guia del trabajo
        assertFalse(envioService.puedeCambiarseEstado(EstadoEnvio.RECIBIDO, EstadoEnvio.ENTREGADO));
        assertFalse(envioService.puedeCambiarseEstado(EstadoEnvio.EN_RUTA, EstadoEnvio.RECIBIDO));
        assertFalse(envioService.puedeCambiarseEstado(EstadoEnvio.ENTREGADO, EstadoEnvio.RECIBIDO));
    }
    @Test
    public void testObtenerEnvioPorNumeroGuia_EnvioExistente() {
        String numeroGuia = "ABC123";
        when(envioRepository.findByNumeroGuia(numeroGuia)).thenReturn(new Envio());

        Envio result = envioService.obtenerEnvioPorNumeroGuia(numeroGuia);

        assertNotNull(result);
    }

    @Test
    public void testObtenerEnvioPorNumeroGuia_EnvioNoExiste() {
        String numeroGuia = "XYZ789";
        when(envioRepository.findByNumeroGuia(numeroGuia)).thenReturn(null);

        Envio result = envioService.obtenerEnvioPorNumeroGuia(numeroGuia);


        assertNull(result);
    }

    @Test
    public void testObtenerEnvioPorNumeroGuia_NumeroGuiaNuloOVacio() {
        String numeroGuia = null;
        assertThrows(IllegalArgumentException.class, () -> envioService.obtenerEnvioPorNumeroGuia(numeroGuia));
    }

    @Test
    public void testEmpleadoPuedeActualizarEstado_EmpleadoRepartidor() {
        Empleado empleado = new Empleado();
        empleado.setTipoEmpleado(TipoEmpleado.REPARTIDOR);

        boolean result = envioService.empleadoPuedeActualizarEstado(empleado);

        assertTrue(result);
    }
    @Test
    public void testEmpleadoPuedeActualizarEstado_EmpleadoCoordinador() {
        Empleado empleado = new Empleado();
        empleado.setTipoEmpleado(TipoEmpleado.COORDINADOR);

        boolean result = envioService.empleadoPuedeActualizarEstado(empleado);

        assertTrue(result);
    }
    @Test
    public void testEmpleadoPuedeActualizarEstado_EmpleadoOtroTipo() {
        Empleado empleado = new Empleado();
        empleado.setTipoEmpleado(TipoEmpleado.CONDUCTOR);

        boolean result = envioService.empleadoPuedeActualizarEstado(empleado);

        assertFalse(result);
    }



}
