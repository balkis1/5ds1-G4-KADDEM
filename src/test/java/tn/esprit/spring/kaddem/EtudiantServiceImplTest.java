package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class EtudiantServiceImplTest {
    @Autowired
    private IEtudiantService etudiantService;

    @Test
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
}