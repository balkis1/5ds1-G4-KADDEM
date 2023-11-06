package tn.esprit.spring.kaddem;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import tn.esprit.spring.kaddem.controllers.DetailEquipeRestController;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.DetailEquipeDTO;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.services.IDetailEquipeService;
import java.util.List;
    
import static org.junit.jupiter.api.Assertions.*;

   
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class DetailEquipeServiceImplTest {

    @Autowired
    IDetailEquipeService iDetailEquipeService;

    @Autowired
    DetailEquipeRepository detailEquipeRepository;

    static Integer IdDetailEquipe = 0;
   

    @Test
    @Order(2)
    public void testRetrieveAllDetailEquipe() {
        List<DetailEquipe> listDetailEquipe = iDetailEquipeService.retrieveAllDetailEquipes();
        assertEquals(1, listDetailEquipe.size());
    }

    @Test
    @Order(1)
    public void testAddDetailEquipe() {
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO(10 , "science");
        DetailEquipe detailEquipeAdded = iDetailEquipeService.addDetailEquipe(detailEquipeDTO);
        IdDetailEquipe = detailEquipeAdded.getIdDetailEquipe();
        assertEquals(10, detailEquipeAdded.getSalle());
    }

    @Test
    @Order(3)
    public void testUpdateDetailEquipe() {
        DetailEquipeDTO detailEquipeDTOUpdated = new DetailEquipeDTO( 15 , "science");
        DetailEquipe detailEquipeUpdated = iDetailEquipeService.updateDetailEquipe(IdDetailEquipe , detailEquipeDTOUpdated);
        assertEquals(15, detailEquipeUpdated.getSalle());
    }

    @Test
    @Order(5)
    public void delete() {
        detailEquipeRepository.deleteAll();
    }
}
