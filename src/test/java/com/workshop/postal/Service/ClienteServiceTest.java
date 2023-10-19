package com.workshop.postal.Service;

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



import java.util.*;


public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll_ReturnsListOfClientes() {
        List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_WithValidId_ReturnsCliente() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.findById(id);

        assertNotNull(result);
        assertEquals(cliente, result);
        verify(clienteRepository, times(1)).findById(id);
    }



    @Test
    public void testSave_WithValidCliente_ReturnsSavedCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.save(cliente);

        assertNotNull(savedCliente);
        assertEquals(cliente, savedCliente);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testSave_WithNullCliente_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> clienteService.save(null));
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    public void testUpdate_WithValidIdAndCliente_ReturnsUpdatedCliente() {
        Long id = 1L;
        Cliente clienteActualizado = new Cliente();
        when(clienteRepository.existsById(id)).thenReturn(true);
        when(clienteRepository.save(clienteActualizado)).thenReturn(clienteActualizado);

        Cliente updatedCliente = clienteService.update(id, clienteActualizado);

        assertNotNull(updatedCliente);
        assertEquals(clienteActualizado, updatedCliente);
        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, times(1)).save(clienteActualizado);
    }

    @Test
    public void testUpdate_WithInvalidId_ThrowsIllegalStateException() {
        Long id = 1L;
        Cliente clienteActualizado = new Cliente();
        when(clienteRepository.existsById(id)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> clienteService.update(id, clienteActualizado));
        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    public void testUpdate_WithNullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> clienteService.update(null, new Cliente()));
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    public void testUpdate_WithNullCliente_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> clienteService.update(1L, null));
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    public void testDeleteById_WithValidId_DeletesCliente() {
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(true);

        clienteService.deleteById(id);

        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteById_WithInvalidId_ThrowsIllegalStateException() {
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> clienteService.deleteById(id));
        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testDeleteById_WithNullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> clienteService.deleteById(null));
        verify(clienteRepository, never()).existsById(anyLong());
        verify(clienteRepository, never()).deleteById(anyLong());
    }
}

