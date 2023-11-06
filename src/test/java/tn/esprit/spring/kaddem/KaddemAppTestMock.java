package tn.esprit.spring.kaddem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class KaddemAppTestMock {

    @Mock
    private IContratService contratService;
    private ContratRepository contratRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test
    public void testAddContrat() throws ParseException {
        Contrat contrat = new Contrat();
        contrat.setMontantContrat(20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebutContrat = dateFormat.parse("01/05/2002");
        contrat.setDateDebutContrat(dateDebutContrat);
        Date dateFinContrat = dateFormat.parse("30/05/2002");
        contrat.setDateFinContrat(dateFinContrat);
        Specialite specialite = Specialite.CLOUD;  // Replace IA with the desired enum value

        contrat.setSpecialite(specialite);

        // Define the behavior of the mocked etudiantService
        when(contratService.addContrat(any(Contrat.class))).thenReturn(contrat);

        // Test the service that depends on the mocked etudiantService
        Contrat result = contratService.addContrat(contrat);

        // Verify that the mocked service was called with the correct arguments
        verify(contratService, times(1)).addContrat(any(Contrat.class));

        // Check if the result matches the expected values
        assertEquals(20, result.getMontantContrat());
        assertEquals(Specialite.CLOUD, result.getSpecialite());
        //assertTrue(contrat.getArchive());
        //assertNotNull(contrat.getIdContrat());

    }

    @Test
    public void testRetrieveAllContrats() {
        List<Contrat> expectedContrats = new ArrayList<>();
        expectedContrats.add(new Contrat());
        expectedContrats.add(new Contrat());

        // Define the behavior of the mocked contratRepository
        when(contratService.retrieveAllContrats()).thenReturn(expectedContrats);

        // Test the service that depends on the mocked contratRepository
        List<Contrat> result = contratService.retrieveAllContrats();

        // Verify that the mocked service was called
        verify(contratService, times(1)).retrieveAllContrats();

        // Check if the result matches the expected list of Contrats
        assertEquals(expectedContrats.size(), result.size());
        // You can add more assertions to check the content of the list
    }

    @Test
    public void testUpdateContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1); // Assuming this is a valid ID

        // Define the behavior of the mocked contratRepository
        when(contratService.updateContrat(any(Contrat.class))).thenReturn(contrat);

        // Test the service that depends on the mocked contratRepository
        Contrat result = contratService.updateContrat(contrat);

        // Verify that the mocked service was called with the correct argument
        verify(contratService, times(1)).updateContrat(any(Contrat.class));

        // Check if the result matches the expected Contrat (it should be the same)
        assertEquals(contrat, result);
    }
    @Test
    public void testRemoveContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1); // Assuming this is a valid ID

        // Define the behavior of the mocked contratRepository for retrieveContrat
        when(contratService.retrieveContrat(eq(1))).thenReturn(contrat);

        // Test the service that depends on the mocked contratRepository
        contratService.removeContrat(1);

        // Verify that the mocked service was called for retrieveContrat and delete
        verify(contratService, times(1)).retrieveContrat(eq(1));
        verify(contratRepository, times(1)).delete(any(Contrat.class));
    }


}
