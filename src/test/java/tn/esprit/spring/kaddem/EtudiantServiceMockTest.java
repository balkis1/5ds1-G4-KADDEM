package tn.esprit.spring.kaddem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EtudiantServiceMockTest {

    @Mock
    private IEtudiantService etudiantService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test
    public void testAddEtudiant() {
        // Create an "Etudiant" object with specific attributes
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Balkis");
        etudiant.setPrenomE("Melki");
        etudiant.setOp(Option.GAMIX);

        // Define the behavior of the mocked etudiantService
        when(etudiantService.addEtudiant(any(Etudiant.class))).thenReturn(etudiant);

        // Test the service that depends on the mocked etudiantService
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Verify that the mocked service was called with the correct arguments
        verify(etudiantService, times(1)).addEtudiant(any(Etudiant.class));

        // Check if the result matches the expected values
        assertEquals("Balkis", result.getNomE());
        assertEquals("Melki", result.getPrenomE());
        assertEquals(Option.GAMIX, result.getOp());
    }
}
