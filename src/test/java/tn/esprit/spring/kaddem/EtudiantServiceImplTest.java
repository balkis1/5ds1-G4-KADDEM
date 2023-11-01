package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class EtudiantServiceImplTest {
    @Autowired
    private IEtudiantService etudiantService;
    private EquipeRepository equipeRepository;
    private ContratRepository contratRepository;

    @Test
    @Order(1)
    void testAddEtudiant() {
        // Create an "Etudiant" object with specific attributes
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1); // Set the ID to 1
        etudiant.setNomE("Balkis"); // Set the name to "Balkis"
        etudiant.setPrenomE("Melki"); // Set the prenom
        etudiant.setOp(Option.GAMIX); // Set the option (replace "YourOptionHere" with the actual option)

        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Check if the entity was successfully added
        assertNotNull(result.getIdEtudiant());
        assertEquals("Balkis", result.getNomE());
        assertEquals("Melki", result.getPrenomE());
        assertEquals(Option.GAMIX, result.getOp());
    }
    @Test
    @Order(2)
    void testRemoveEtudiant() {
        // Replace 1 with the ID of an existing Etudiant in your database that you want to remove
        Integer existingEtudiantId = 1;

        // Remove the Etudiant
        etudiantService.removeEtudiant(existingEtudiantId);

        // Attempt to retrieve the removed Etudiant
        Etudiant removedEtudiant = etudiantService.retrieveEtudiant(existingEtudiantId);

        // Check if the removed Etudiant is null (indicating it was successfully removed)
        assertNull(removedEtudiant);
    }
    @Test
    @Order(3)
    void testAssignEtudiantToDepartement() {
        // Replace etudiantId and departementId with actual IDs of an existing Etudiant and Departement
        Integer etudiantId = 1;
        Integer departementId = 2;

        // Assign the Etudiant to the Departement
        etudiantService.assignEtudiantToDepartement(etudiantId, departementId);

        // Retrieve the Etudiant to check if it is assigned to the Departement
        Etudiant etudiant = etudiantService.retrieveEtudiant(etudiantId);

        // Check if the Etudiant is assigned to the specified Departement
        assertNotNull(etudiant.getDepartement());
        assertEquals(departementId, etudiant.getDepartement().getIdDepart());
    }
    @Test
    @Order(4)
    void testUpdateEtudiant() {
        // Replace 1 with the ID of an existing Etudiant in your database that you want to update
        Integer existingEtudiantId = 1;

        // Retrieve the existing Etudiant
        Etudiant existingEtudiant = etudiantService.retrieveEtudiant(existingEtudiantId);

        // Modify the attributes of the existing Etudiant
        existingEtudiant.setNomE("UpdatedName");
        existingEtudiant.setPrenomE("UpdatedPrenom");

        // Update the Etudiant
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(existingEtudiant);

        // Retrieve the updated Etudiant from the database
        Etudiant retrievedUpdatedEtudiant = etudiantService.retrieveEtudiant(existingEtudiantId);

        // Check if the attributes of the retrieved Etudiant match the updated values
        assertEquals("UpdatedName", retrievedUpdatedEtudiant.getNomE());
        assertEquals("UpdatedPrenom", retrievedUpdatedEtudiant.getPrenomE());
    }
    @Test
    @Order(5)
    void testaddAndAssignEtudiantToEquipeAndContract() {
        // Create an "Etudiant" object with specific attributes
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("Ahmed");
        etudiant.setPrenomE("Melkii");

        // Replace idContrat and idEquipe with actual IDs of existing Contrat and Equipe entities
        Integer idContrat = 1;
        Integer idEquipe = 2;

        // Add and assign the Etudiant to the Contrat and Equipe
        Etudiant addedEtudiant = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, idContrat, idEquipe);

        // Retrieve the Contrat and Equipe to check if the Etudiant is correctly assigned
        Contrat contrat = contratRepository.findById(idContrat).orElse(null);
        Equipe equipe = equipeRepository.findById(idEquipe).orElse(null);

        // Check if the Etudiant is assigned to the Contrat and Equipe
        assertNotNull(contrat);
        assertNotNull(equipe);
        assertNotNull(addedEtudiant);
        assertEquals(addedEtudiant, contrat.getEtudiant());
        assertTrue(equipe.getEtudiants().contains(addedEtudiant));
    }
}



