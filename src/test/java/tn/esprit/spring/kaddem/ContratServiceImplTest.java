package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ContratServiceImplTest {
    @Autowired
    private IContratService contratService;

    @Test
    public void testAddContrat() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebutContrat = dateFormat.parse("1/06/2000");
        Date dateFinContrat = dateFormat.parse("30/09/2000");

        Specialite specialite = Specialite.IA;  // Replace IA with the desired enum value

        // Replace "True" with true
        boolean archive = true;

        Contrat c = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, 20);

        Contrat contrat = contratService.addContrat(c);
        log.info("contraaat "+contrat);
        assertNotNull(contrat.getIdContrat());
        assertTrue(contrat.getArchive());

    }
}
