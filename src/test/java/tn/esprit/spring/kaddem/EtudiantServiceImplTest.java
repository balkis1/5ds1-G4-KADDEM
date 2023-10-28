package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EtudiantServiceImplTest {
    @Autowired
    private IEtudiantService etudiantService;

    @Test
    public void testAddEtudiant() {
        // Create an "Etudiant" object with specific attributes
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1); // Set the ID to 1
        etudiant.setNomE("Ahmed"); // Set the name to "Ahmed"
        etudiant.setPrenomE("Melki"); // Set the prenom
        etudiant.setOp(Option.NIDS); // Set the option

        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Check if the entity was successfully added
        assertNotNull(result.getIdEtudiant());
        assertEquals("Ahmed", result.getNomE());
        assertEquals("Melki", result.getPrenomE());
        assertEquals(Option.NIDS, result.getOp());
    }
}
