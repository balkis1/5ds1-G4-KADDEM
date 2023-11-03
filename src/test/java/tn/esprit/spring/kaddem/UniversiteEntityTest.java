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
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.entities.UniversiteDTO;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(Universite.class)
public class UniversiteEntityTest {

    private MockMvc mockMvc;

    @Mock
    private Universite univ;

    @Mock
    private UniversiteDTO univDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(univ).build();
    }

    @Test
    public void detailEquipeSetterTest()
    {
        univ.setIdUniv(1);
        univ.setNomUniv("ESPRIT");
        univDTO.setNomUniv("ESPRIT");
        Assert.assertEquals(univ.getNomUniv(), univDTO.getNomUniv());
    }

}
