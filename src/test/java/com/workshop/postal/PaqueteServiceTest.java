package com.workshop.postal;



    import com.workshop.postal.models.Paquete;
    import com.workshop.postal.models.enums.TipoPaquete;
    import com.workshop.postal.repository.PaqueteRepository;
    import com.workshop.postal.service.PaqueteService;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;
    import java.util.List;
    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertNotNull;
    import static org.mockito.BDDMockito.given;
    import static org.mockito.Mockito.*;

public class PaqueteServiceTest {

    @Mock
    private PaqueteRepository paqueteRepository;

    @InjectMocks
    private PaqueteService paqueteService;

    @BeforeEach
    public void setUp() {MockitoAnnotations.initMocks(this);}

    @Test
    public void testGetAllPaquetes() {

        given(paqueteRepository.findAll()).willReturn(List.of(new Paquete(), new Paquete()));

        List<Paquete> paquetes = paqueteService.getAllPaquetes();


        verify(paqueteRepository, times(1)).findAll();


        assertEquals(2, paquetes.size());
    }

    @Test
    public void testGetPaqueteById() {
        Long id = 1L;
        Paquete paquete = new Paquete();
        given(paqueteRepository.findById(id)).willReturn(Optional.of(paquete));

        Optional<Paquete> result = paqueteService.getPaqueteById(id);


        verify(paqueteRepository, times(1)).findById(id);


        assertEquals(paquete, result.get());
    }

    @Test
    public void testCreatePaquete() {
        Paquete paquete = new Paquete();
        paquete.setPeso(2.0);
        paquete.setTipoPaquete(TipoPaquete.GRANDE);
        paquete.setValorDeclarado(20.000);


        given(paqueteRepository.save(paquete)).willReturn(paquete);

        Paquete result = paqueteService.createPaquete(paquete);


        verify(paqueteRepository, times(1)).save(paquete);


        assertEquals(paquete, result);
    }

    @Test
    public void testDeletePaquete() {
        Long id = 1L;

        paqueteService.deletePaquete(id);


        verify(paqueteRepository, times(1)).deleteById(id);
    }
    @Test
    public void testUpdatePaquete() {
        Long id = 1L;
        Paquete paqueteActualizado = new Paquete();


        when(paqueteRepository.save(paqueteActualizado)).thenReturn(paqueteActualizado);

        Paquete updatedPaquete = paqueteService.updatePaquete(id, paqueteActualizado);


        verify(paqueteRepository, times(1)).save(paqueteActualizado);


        assertNotNull(updatedPaquete);
        assertEquals(paqueteActualizado, updatedPaquete);
    }


}