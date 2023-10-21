package com.workshop.postal.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.workshop.postal.exceptions.BusinessException;
import com.workshop.postal.models.Empleado;
import com.workshop.postal.repository.EmpleadoRepository;
import com.workshop.postal.service.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.*;


public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll_WhenEmpleadosExist_ReturnsListOfEmpleados() {
        List<Empleado> empleadosMock = Arrays.asList(new Empleado(), new Empleado());
        when(empleadoRepository.findAll()).thenReturn(empleadosMock);

        List<Empleado> empleados = empleadoService.findAll();

        verify(empleadoRepository, times(1)).findAll();
        assertNotNull(empleados);
        assertEquals(2, empleados.size());
        for (Empleado empleado : empleados) {
            assertNotNull(empleado);
        }
    }

    @Test
    public void testFindAll_WhenNoEmpleadosExist_ReturnsEmptyList() {
        when(empleadoRepository.findAll()).thenReturn(Collections.emptyList());

        List<Empleado> empleados = empleadoService.findAll();

        verify(empleadoRepository, times(1)).findAll();
        assertNotNull(empleados);
        assertEquals(0, empleados.size());
    }

    @Test
    @Disabled
    public void testFindById_WithValidId_ReturnsEmpleado() {
        Long id = 1L;
        Empleado empleadoMock = new Empleado();
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleadoMock));

        Empleado result = empleadoService.findById(id);

        verify(empleadoRepository, times(1)).findById(id);
        assertNotNull(result);
        assertEquals(empleadoMock, result);
    }

    @Test
    public void testFindById_WithInvalidId_ThrowsBusinessException() {
        Long id = 1L;
        when(empleadoRepository.findById(id)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> empleadoService.findById(id));
        assertEquals("El empleado no existe", exception.getMessage());
        verify(empleadoRepository, times(1)).findById(id);
    }

    @Test
    public void testSave_WithValidEmpleado_ReturnsSavedEmpleado() {
        Empleado empleadoMock = new Empleado();
        when(empleadoRepository.save(empleadoMock)).thenReturn(empleadoMock);

        Empleado savedEmpleado = empleadoService.save(empleadoMock);

        verify(empleadoRepository, times(1)).save(empleadoMock);
        assertNotNull(savedEmpleado);
        assertEquals(empleadoMock, savedEmpleado);
    }

    @Test
    public void testSave_WithNullEmpleado_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> empleadoService.save(null));
        verify(empleadoRepository, never()).save(any(Empleado.class));
    }

    @Test
    public void testUpdate_WithValidIdAndEmpleado_ReturnsUpdatedEmpleado() {
        Long id = 1L;
        Empleado empleadoMock = new Empleado();
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleadoMock));
        when(empleadoRepository.save(empleadoMock)).thenReturn(empleadoMock);

        Empleado updatedEmpleado = empleadoService.update(id, empleadoMock);

        verify(empleadoRepository, times(1)).findById(id);
        verify(empleadoRepository, times(1)).save(empleadoMock);
        assertNotNull(updatedEmpleado);
        assertEquals(empleadoMock, updatedEmpleado);
    }

    @Test
    public void testUpdate_WithInvalidId_ThrowsBusinessException() {
        Long id = 1L;
        Empleado empleadoMock = new Empleado();
        when(empleadoRepository.findById(id)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> empleadoService.update(id, empleadoMock));
        assertEquals("El empleado no existe", exception.getMessage());
        verify(empleadoRepository, times(1)).findById(id);
        verify(empleadoRepository, never()).save(any(Empleado.class));
    }

    @Test
    public void testUpdate_WithNullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> empleadoService.update(null, new Empleado()));
        verify(empleadoRepository, never()).save(any(Empleado.class));
    }

    @Test
    public void testUpdate_WithNullEmpleado_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> empleadoService.update(1L, null));
        verify(empleadoRepository, never()).save(any(Empleado.class));
    }

    @Test
    public void testDeleteById_WithValidId_DeletesEmpleado() {
        Long id = 1L;
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(new Empleado()));

        empleadoService.deleteById(id);

        verify(empleadoRepository, times(1)).findById(id);
        verify(empleadoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteById_WithInvalidId_ThrowsBusinessException() {
        Long id = 1L;
        when(empleadoRepository.findById(id)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> empleadoService.deleteById(id));
        assertEquals("El empleado no existe", exception.getMessage());
        verify(empleadoRepository, times(1)).findById(id);
        verify(empleadoRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testDeleteById_WithNullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> empleadoService.deleteById(null));
        verify(empleadoRepository, never()).findById(anyLong());
        verify(empleadoRepository, never()).deleteById(anyLong());
    }
}

