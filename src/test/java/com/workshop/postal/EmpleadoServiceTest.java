package com.workshop.postal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.workshop.postal.models.Cliente;
import com.workshop.postal.models.Empleado;
import com.workshop.postal.repository.EmpleadoRepository;
import com.workshop.postal.service.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        // Simulamos el comportamiento del repositorio
        when(empleadoRepository.findAll()).thenReturn(List.of(new Empleado(), new Empleado()));

        List<Empleado> empleados = empleadoService.findAll();

        // Verificamos que se llamó al método findAll del repositorio
        verify(empleadoRepository, times(1)).findAll();

        // Aserciones
        assertNotNull(empleados);
        assertEquals(2, empleados.size());
        for (Empleado empleado : empleados) {
            assertNotNull(empleado);
        }
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Empleado empleado = new Empleado();
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleado));

        Empleado result = empleadoService.findById(id);


        verify(empleadoRepository, times(1)).findById(id);


        assertNotNull(result);
        assertEquals(empleado, result);
    }

    @Test
    public void testSave() {
        Empleado empleado = new Empleado();
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        Empleado savedEmpleado = empleadoService.save(empleado);

        // Verificamos que se llamó al método save del repositorio
        verify(empleadoRepository, times(1)).save(empleado);

        // Aserciones
        assertNotNull(savedEmpleado);
        assertEquals(empleado, savedEmpleado);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        Empleado empleadoActualizado = new Empleado();
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleadoActualizado));
        when(empleadoRepository.save(empleadoActualizado)).thenReturn(empleadoActualizado);

        Empleado updatedEmpleado = empleadoService.update(id, empleadoActualizado);

        // Verificamos que se llamó al método findById del repositorio
        verify(empleadoRepository, times(1)).findById(id);

        // Verificamos que se llamó al método save del repositorio
        verify(empleadoRepository, times(1)).save(empleadoActualizado);

        // Aserciones
        assertNotNull(updatedEmpleado);
        assertEquals(empleadoActualizado, updatedEmpleado);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(new Empleado()));

        empleadoService.deleteById(id);

        // Verificamos que se llamó al método findById del repositorio
        verify(empleadoRepository, times(1)).findById(id);

        // Verificamos que se llamó al método deleteById del repositorio
        verify(empleadoRepository, times(1)).deleteById(id);
    }
}
