package com.workshop.postal.Service;

import com.workshop.postal.dtos.EnvioDto;
import com.workshop.postal.dtos.EnvioRecibidoDto;
import com.workshop.postal.exceptions.BusinessException;
import com.workshop.postal.models.Cliente;
import com.workshop.postal.models.Envio;
import com.workshop.postal.models.Paquete;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CrearEnvioServiceTest {
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
        envioService = new EnvioService(envioRepository,clienteRepository,empleadoRepository,paqueteService );
    }
    @Test
    public void testCrearEnvioExitoso() throws BusinessException {
        EnvioDto request = new EnvioDto();
        request.setCedulaCliente("123456");
        request.setCiudadOrigen("Medellin");
        request.setCiudadDestino("Bogota");
        request.setPeso(2.0);
        request.setDireccionDestino("calle 9 e");
        request.setCelular("311321");
        request.setNombreRecibe("Juan");
        request.setValorDeclaradoPaquete(2000);

        Cliente clienteMock = new Cliente();
        Paquete paqueteMock = new Paquete();

        when(clienteRepository.findByCedula(any())).thenReturn(clienteMock);
        when(paqueteService.createPaquete(any())).thenReturn(paqueteMock);
        when(envioRepository.save(any())).thenReturn(new Envio());

        EnvioRecibidoDto result = envioService.crearEnvio(request);

        assertNotNull(result);

    }






}