package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@Service
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KaddemApplication.class)

public class KaddemApplicationTest {
    @Autowired
    IContratService contratService;
    @Test
    public void testAddContat() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebutContrat = dateFormat.parse("1/09/2000");
        Date dateFinContrat = dateFormat.parse("30/09/2000");

        Specialite specialite = Specialite.IA;  // Replace IA with the desired enum value

        // Replace "True" with true
        boolean archive = true;

        Contrat c = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, 15);

        Contrat contrat = contratService.addContrat(c);
        log.info("contraaat "+contrat);
        assertNotNull(contrat.getIdContrat());
        assertTrue(contrat.getArchive());

    } }