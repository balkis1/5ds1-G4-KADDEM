package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.DetailEquipeDTO;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.services.DetailEquipeServiceImpl;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(DetailEquipeServiceImpl.class)
public class DetailEquipeServiceTest {



    private MockMvc mockMvc;

    @Mock
    private DetailEquipeRepository detailEquipeRepository;

    @InjectMocks
    private DetailEquipeServiceImpl detailEquipeServiceImpl;

    DetailEquipe detailEquipe = DetailEquipe.builder().idDetailEquipe(1).salle(1).thematique("thematique1").build();

    DetailEquipeDTO detailEquipeDTO = DetailEquipeDTO.builder().salle(1).thematique("thematique1").build();

    List<DetailEquipe> records = new ArrayList<DetailEquipe>() {
        {
            add(DetailEquipe.builder().idDetailEquipe(1).salle(1).thematique("thematique1").build());
            add(DetailEquipe.builder().idDetailEquipe(2).salle(2).thematique("thematique2").build());
            add(DetailEquipe.builder().idDetailEquipe(3).salle(3).thematique("thematique3").build());
        }
    };
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(detailEquipeServiceImpl).build();
    }

    @Test
    public void retrieveDetailEquipesServiceTest() {
        Mockito.when(detailEquipeRepository.findAll()).thenReturn(records);
        List<DetailEquipe> detailEquipes = detailEquipeServiceImpl.retrieveAllDetailEquipes();
        Assertions.assertEquals(3, detailEquipes.size());
        Assertions.assertEquals("thematique1", detailEquipes.get(0).getThematique());
    }

    @Test
    public void retrieveDetailEquipeServiceTest() {
        Mockito.when(detailEquipeRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(detailEquipe));
        DetailEquipe detailEquipe1 = detailEquipeServiceImpl.retrieveDetailEquipe(1);
        Assert.assertNotNull(detailEquipe1);
        Assertions.assertEquals("thematique1", detailEquipe1.getThematique());
    }

    @Test
    public void addDetailEquipeServiceTest() {
        Mockito.when(detailEquipeRepository.save(Mockito.any())).thenReturn(detailEquipe);
        DetailEquipe detailEquipe1 = detailEquipeServiceImpl.addDetailEquipe(detailEquipeDTO);
        Assert.assertNotNull(detailEquipe1);
    }

     @Test
    public void updateDetailEquipeServiceTest() {
        DetailEquipe detailEquipeUpdated = DetailEquipe.builder().idDetailEquipe(2).salle(1).thematique("thematique1").build();
        Mockito.when(detailEquipeRepository.findDetailEquipeByIdDetailEquipe(1)).thenReturn(detailEquipe);
        Mockito.when(detailEquipeRepository.save(detailEquipe)).thenReturn(detailEquipeUpdated);
        DetailEquipe detailEquipe1 = detailEquipeServiceImpl.updateDetailEquipe(1, detailEquipeDTO);
        Assert.assertNotNull(detailEquipe1);
        Assertions.assertEquals("thematique1", detailEquipe1.getThematique());
    }

    @Test
    public void deleteDetailEquipeServiceTest() {
        Mockito.when(detailEquipeRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(detailEquipe));
        detailEquipeServiceImpl.removeDetailEquipe(1);
        Mockito.verify(detailEquipeRepository, Mockito.times(1)).delete(detailEquipe);
    }
}
