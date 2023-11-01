package tn.esprit.spring.kaddem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KaddemApplication.class)
public class KaddemApplicationTest {
    @Autowired
    IContratService contratService;

    @Test
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
}
