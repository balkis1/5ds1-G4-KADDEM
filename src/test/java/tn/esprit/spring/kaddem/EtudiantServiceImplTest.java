package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class EtudiantServiceImplTest {
    @Autowired
    private IEtudiantService etudiantService;

    @Test
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        Etudiant result = etudiantService.addEtudiant(etudiant);
        assertNotNull(result.getIdEtudiant());
    }


}
