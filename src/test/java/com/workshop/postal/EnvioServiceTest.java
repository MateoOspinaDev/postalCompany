package com.workshop.postal;

import static org.junit.jupiter.api.Assertions.*;

import com.workshop.postal.models.enums.EstadoEnvio;

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
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
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
        MockitoAnnotations.initMocks(this);
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



}

