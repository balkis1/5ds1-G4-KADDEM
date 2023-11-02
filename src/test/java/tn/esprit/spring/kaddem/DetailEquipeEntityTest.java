package tn.esprit.spring.kaddem;


import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.DetailEquipeDTO;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(DetailEquipe.class)
public class DetailEquipeEntityTest {

    private MockMvc mockMvc;

    @Mock
    private DetailEquipe detailEquipe;

    @Mock
    private DetailEquipeDTO detailEquipeDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(detailEquipe).build();
    }

    @Test
    public void detailEquipeSetterTest()
    {
        detailEquipe.setIdDetailEquipe(1);
        detailEquipe.setSalle(1);
        detailEquipe.setThematique("thematique1");
        detailEquipeDTO.setSalle(1);
        detailEquipeDTO.setThematique("thematique1");
        Assert.assertEquals(detailEquipe.getSalle(), detailEquipeDTO.getSalle());
        Assert.assertEquals(detailEquipe.getThematique(), detailEquipeDTO.getThematique());
    }

}
