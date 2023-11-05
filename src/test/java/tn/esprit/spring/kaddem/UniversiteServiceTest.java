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
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.entities.UniversiteDTO;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(UniversiteServiceImpl.class)
public class UniversiteServiceTest {



    private MockMvc mockMvc;

    @Mock
    private UniversiteRepository UniversiteRepository;

    @InjectMocks
    private UniversiteServiceImpl UniversiteServiceImpl;

    Universite universite = Universite.builder().idUniv(1).nomUniv("ESPRIT").build();

    UniversiteDTO universiteDTO = UniversiteDTO.builder().nomUniv("ESPRIT").build();

    List<Universite> records = new ArrayList<Universite>() {
        {
            add(Universite.builder().idUniv(1).nomUniv("ESPRIT").build());
            add(Universite.builder().idUniv(2).nomUniv("ESPRITT").build());
            add(Universite.builder().idUniv(3).nomUniv("ESPRITTT").build());
        }
    };
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(UniversiteServiceImpl).build();
    }

    @Test
    public void retrieveUniversitesServiceTest() {
        Mockito.when(UniversiteRepository.findAll()).thenReturn(records);
        List<Universite> Universites = UniversiteServiceImpl.retrieveAllUniversites();
        Assertions.assertEquals(3, Universites.size());
        Assertions.assertEquals("ESPRIT", Universites.get(0).getNomUniv());
    }

    @Test
    public void retrieveUniversiteServiceTest() {
        Mockito.when(UniversiteRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(universite));
        Universite Universite1 = UniversiteServiceImpl.retrieveUniversite(1);
        Assert.assertNotNull(Universite1);
        Assertions.assertEquals("ESPRIT", Universite1.getNomUniv());
    }

    @Test
    public void addUniversiteServiceTest() {
        Mockito.when(UniversiteRepository.save(Mockito.any())).thenReturn(universite);
        Universite Universite1 = UniversiteServiceImpl.addUniversite(universiteDTO);
        Assert.assertNotNull(Universite1);
    }

    @Test
    public void updateUniversiteServiceTest() {
        Universite UniversiteUpdated = Universite.builder().idUniv(2).nomUniv("ESPRIT").build();
        Mockito.when(UniversiteRepository.findUniversiteByIdUniv(1)).thenReturn(universite);
        Mockito.when(UniversiteRepository.save(universite)).thenReturn(UniversiteUpdated);
        Universite Universite1 = UniversiteServiceImpl.updateUniversite(1, universiteDTO);
        Assert.assertNotNull(Universite1);
        Assertions.assertEquals("ESPRIT", Universite1.getNomUniv());
    }

    @Test
    public void deleteUniversiteServiceTest() {
        Mockito.when(UniversiteRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(universite));
        UniversiteServiceImpl.deleteUniversite(1);
        Mockito.verify(UniversiteRepository, Mockito.times(1)).delete(universite);
    }
}
