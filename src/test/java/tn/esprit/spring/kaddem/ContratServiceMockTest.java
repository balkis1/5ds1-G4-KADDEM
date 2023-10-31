package tn.esprit.spring.kaddem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.services.IContratService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ContratServiceMockTest {

    @Mock
    private IContratService contratService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test
    public void testAddContrat() throws ParseException{
        // Create an "Etudiant" object with specific attributes
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebutContrat = dateFormat.parse("1/06/2000");
        Date dateFinContrat = dateFormat.parse("30/09/2000");

        Specialite specialite = Specialite.CLOUD;  // Replace IA with the desired enum value

        // Replace "True" with true
        boolean archive = true;

        Contrat c = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, 20);

        Contrat contrat = contratService.addContrat(c);
        //assertNotNull(contrat.getIdContrat());
//        assertTrue(contrat.getArchive());
        
        // Define the behavior of the mocked etudiantService
        when(contratService.addContrat(any(Contrat.class))).thenReturn(contrat);

        // Test the service that depends on the mocked etudiantService
        Contrat result = contratService.addContrat(contrat);

        // Verify that the mocked service was called with the correct arguments
        verify(contratService, times(1)).addContrat(any(Contrat.class));
        
        // Check if the result matches the expected values
       // assertEquals("1/06/2000", result.getDateDebutContrat());
        //assertEquals("30/09/2000", result.getDateFinContrat());
        //assertEquals(Specialite.IA, result.getSpecialite());
        //assertTrue(contrat.getArchive());
        //assertNotNull(contrat.getIdContrat());

    }
}
