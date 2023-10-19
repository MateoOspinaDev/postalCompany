package com.workshop.postal.Service;



import com.workshop.postal.models.Paquete;
import com.workshop.postal.models.enums.TipoPaquete;
import com.workshop.postal.repository.PaqueteRepository;
import com.workshop.postal.service.PaqueteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaqueteServiceTest {

    @InjectMocks
    private PaqueteService paqueteService;

    @Mock
    private PaqueteRepository paqueteRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testGetAllPaquetes() {
        List<Paquete> paquetes = Arrays.asList(new Paquete(), new Paquete());
        when(paqueteRepository.findAll()).thenReturn(paquetes);

        List<Paquete> result = paqueteService.getAllPaquetes();

        assertEquals(2, result.size());
        verify(paqueteRepository, times(1)).findAll();
    }

    @Test
    public void testGetPaqueteById_WithValidId_ReturnsOptionalOfPaquete() {
        Long id = 1L;
        Paquete paquete = new Paquete();
        when(paqueteRepository.findById(id)).thenReturn(Optional.of(paquete));

        Optional<Paquete> result = paqueteService.getPaqueteById(id);

        assertTrue(result.isPresent());
        assertEquals(paquete, result.get());
        verify(paqueteRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPaqueteById_WithInvalidId_ReturnsEmptyOptional() {
        Long id = 1L;
        when(paqueteRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Paquete> result = paqueteService.getPaqueteById(id);

        assertFalse(result.isPresent());
        verify(paqueteRepository, times(1)).findById(id);
    }

    @Test
    public void testCreatePaquete_WithValidPaquete_ReturnsCreatedPaquete() {
        Paquete paquete = new Paquete();
        paquete.setPeso(2.0);
        paquete.setTipoPaquete(TipoPaquete.GRANDE);
        paquete.setValorDeclarado(20.000);

        when(paqueteRepository.save(paquete)).thenReturn(paquete);

        Paquete result = paqueteService.createPaquete(paquete);

        assertNotNull(result);
        assertEquals(paquete, result);
        verify(paqueteRepository, times(1)).save(paquete);
    }

    @Test
    public void testCreatePaquete_WithNullPaquete_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> paqueteService.createPaquete(null));
    }

    @Test
    public void testUpdatePaquete_WithValidIdAndPaquete_ReturnsUpdatedPaquete() {
        Long id = 1L;
        Paquete paqueteActualizado = new Paquete();

        when(paqueteRepository.save(paqueteActualizado)).thenReturn(paqueteActualizado);

        Paquete updatedPaquete = paqueteService.updatePaquete(id, paqueteActualizado);

        assertNotNull(updatedPaquete);
        assertEquals(paqueteActualizado, updatedPaquete);
        verify(paqueteRepository, times(1)).save(paqueteActualizado);
    }

    @Test
    public void testUpdatePaquete_WithNullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> paqueteService.updatePaquete(null, new Paquete()));
    }

    @Test
    public void testUpdatePaquete_WithNullPaquete_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> paqueteService.updatePaquete(1L, null));
    }

    @Test
    public void testDeletePaquete_WithValidId_DeletesPaquete() {
        Long id = 1L;

        paqueteService.deletePaquete(id);

        verify(paqueteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeletePaquete_WithNullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> paqueteService.deletePaquete(null));
    }
}
