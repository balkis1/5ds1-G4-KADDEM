package tn.esprit.spring.kaddem;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KaddemApplication.class)
public class KaddemApplicationTest {
    @Autowired
    IContratService contratService;

    @Test
    @Order(1)
    public void testAddContrat() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebutContrat = dateFormat.parse("1/09/2000");
        Date dateFinContrat = dateFormat.parse("30/09/2000");

        Specialite specialite = Specialite.IA;

        boolean archive = true;

        Contrat c = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, 15);

        Contrat contrat = contratService.addContrat(c);
        assertNotNull(contrat.getIdContrat());
        assertTrue(contrat.getArchive());
    }

    @Test
    @Order(2)
    public void testRemoveContrat() {
        Integer existingContratId = 1;

        contratService.removeContrat(existingContratId);

        // Attempt to retrieve the removed contrat
        Contrat removedContrat = contratService.retrieveContrat(existingContratId);

        // Check if the removed contrat is null (indicating it was successfully removed)
        assertNull(removedContrat);
    }


    @Test
    @Order(3)

    public void testUpdateContrat() throws ParseException {
        // Create a new Contrat and add it to the database
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebutContrat = dateFormat.parse("1/09/2022");
        Date dateFinContrat = dateFormat.parse("30/09/2022");
        Specialite specialite = Specialite.IA;
        boolean archive = true;

        Contrat c = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, 15);
        Contrat addedContrat = contratService.addContrat(c);

        // Modify the Contrat
        addedContrat.setArchive(false);
        Contrat updatedContrat = contratService.updateContrat(addedContrat);

        // Retrieve the updated Contrat from the database
        Contrat retrievedContrat = contratService.retrieveContrat(updatedContrat.getIdContrat());

        // Check if the Contrat was updated successfully
        assertNotNull(updatedContrat);
        assertFalse(updatedContrat.getArchive());
        assertNotNull(retrievedContrat);
        assertEquals(updatedContrat.getIdContrat(), retrievedContrat.getIdContrat());

        // Clean up - remove the test Contrat
        contratService.removeContrat(updatedContrat.getIdContrat());
    }

    @Test
    @Order(4)
    public void testRetrieveAllContrats() {
        List<Contrat> allContrats = contratService.retrieveAllContrats();

        // Check if the list is not null and contains at least one Contrat
        assertNotNull(allContrats);
        assertTrue(allContrats.size() > 0);
    }
    @Test
    @Order(5)
    public void testRetrieveContrat() throws ParseException {
        // Add a Contrat to the database
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebutContrat = dateFormat.parse("1/09/2022");
        Date dateFinContrat = dateFormat.parse("30/09/2022");
        Specialite specialite = Specialite.IA;
        boolean archive = true;

        Contrat c = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, 15);
        Contrat addedContrat = contratService.addContrat(c);

        // Retrieve the added Contrat using its ID
        Contrat retrievedContrat = contratService.retrieveContrat(addedContrat.getIdContrat());

        // Check if the retrieved Contrat matches the added Contrat
        assertNotNull(retrievedContrat);
        assertEquals(addedContrat.getIdContrat(), retrievedContrat.getIdContrat());

        // Clean up - remove the test Contrat
        contratService.removeContrat(retrievedContrat.getIdContrat());
    }




}
