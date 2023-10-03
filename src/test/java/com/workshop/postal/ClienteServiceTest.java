package com.workshop.postal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.workshop.postal.models.Cliente;
import com.workshop.postal.repository.ClienteRepository;
import com.workshop.postal.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {

        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente(), new Cliente()));

        List<Cliente> clientes = clienteService.findAll();


        verify(clienteRepository, times(1)).findAll();


        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        for (Cliente cliente : clientes) {
            assertNotNull(cliente);
        }
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.findById(id);


        verify(clienteRepository, times(1)).findById(id);


        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.save(cliente);


        verify(clienteRepository, times(1)).save(cliente);


        assertNotNull(savedCliente);
        assertEquals(cliente, savedCliente);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        Cliente clienteActualizado = new Cliente();
        when(clienteRepository.existsById(id)).thenReturn(true);
        when(clienteRepository.save(clienteActualizado)).thenReturn(clienteActualizado);

        Cliente updatedCliente = clienteService.update(id, clienteActualizado);


        verify(clienteRepository, times(1)).existsById(id);


        verify(clienteRepository, times(1)).save(clienteActualizado);


        assertNotNull(updatedCliente);
        assertEquals(clienteActualizado, updatedCliente);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(true);

        clienteService.deleteById(id);


        verify(clienteRepository, times(1)).existsById(id);

        verify(clienteRepository, times(1)).deleteById(id);
    }
}

