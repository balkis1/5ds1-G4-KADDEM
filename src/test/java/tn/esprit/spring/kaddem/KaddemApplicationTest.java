package tn.esprit.spring.kaddem;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KaddemApplication.class)
public class KaddemApplicationTest {
    @Autowired
    IContratService contratService;
    EtudiantRepository etudiantRepository;

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
    void testRemoveContrat() {
        Integer existingContratId = 1;

        contratService.removeContrat(existingContratId);

        // Attempt to retrieve the removed contrat
        Contrat removedContrat = contratService.retrieveContrat(existingContratId);

        // Check if the removed contrat is null (indicating it was successfully removed)
        assertNull(removedContrat);
    }
    @Test
    @Order(3)
    void testAffectContratToEtudiant() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebutContrat = null;
        Date dateFinContrat = null;

        try {
            dateDebutContrat = dateFormat.parse("1/09/2000");
            dateFinContrat = dateFormat.parse("30/09/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Specialite specialite = Specialite.IA;
        boolean archive = true;
        Contrat contrat = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, 15);

        // Add a new student (Etudiant) to the database or use an existing one
        Etudiant etudiant = new Etudiant(); // Replace with actual code to create an Etudiant

        // Save the student to the repository or use an existing student
        etudiant = etudiantRepository.save(etudiant);

        // Assign the contract to the student
        Contrat assignedContrat = contratService.affectContratToEtudiant(contrat.getIdContrat(), etudiant.getNomE(), etudiant.getPrenomE());

        assertNotNull(assignedContrat);
        assertEquals(etudiant, assignedContrat.getEtudiant());
    }



    @Test
    @Order(4)
    void testRetrieveAndUpdateStatusContrat() {
        // Add test data - create a contract that is 15 days from expiration
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        Date dateDebutContrat = new Date(currentDate.getTime() - (15 * 24 * 60 * 60 * 1000)); // 15 days ago
        Date dateFinContrat = currentDate;
        Specialite specialite = Specialite.IA;
        boolean archive = false;

        Contrat c = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, 15);

        Contrat contract15DaysFromExpiration = contratService.addContrat(c);

        // Invoke the method to update contract status
        contratService.retrieveAndUpdateStatusContrat();

        // Check if the contract is now archived
        Contrat updatedContract = contratService.retrieveContrat(contract15DaysFromExpiration.getIdContrat());
        assertNotNull(updatedContract);
        assertTrue(updatedContract.getArchive());

        // Clean up - remove the test contract
        contratService.removeContrat(contract15DaysFromExpiration.getIdContrat());
    }
    @Test
    @Order(5)
    void testGetChiffreAffaireEntreDeuxDates() {
        // Create contracts with different specializations and date ranges
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        Date endDate = null;

        try {
            startDate = dateFormat.parse("01/01/2023");
            endDate = dateFormat.parse("31/01/2023");

            // Create contracts with different specializations
            Contrat iaContract = new Contrat(startDate, endDate, Specialite.IA, false, 15);
            Contrat cloudContract = new Contrat(startDate, endDate, Specialite.CLOUD, false, 10);
            Contrat reseauxContract = new Contrat(startDate, endDate, Specialite.RESEAUX, false, 12);
            Contrat securiteContract = new Contrat(startDate, endDate, Specialite.SECURITE, false, 8);

            // Save the contracts in the database
            contratService.addContrat(iaContract);
            contratService.addContrat(cloudContract);
            contratService.addContrat(reseauxContract);
            contratService.addContrat(securiteContract);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Calculate the expected result
        float expectedChiffreAffaire = (31 * 300) + (31 * 400) + (31 * 350) + (31 * 450);

        // Call the function to calculate the actual result
        float actualChiffreAffaire = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        // Assert the expected result matches the actual result with a tolerance for floating-point comparisons
        assertEquals(expectedChiffreAffaire, actualChiffreAffaire, 0.01);
    }
}
