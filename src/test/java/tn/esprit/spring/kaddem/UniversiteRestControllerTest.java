package tn.esprit.spring.kaddem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.entities.UniversiteDTO;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.datatype.jsr310.*;
import org.xmlunit.diff.Comparison;
import tn.esprit.spring.kaddem.controllers.UniversiteRestController;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.IUniversiteService;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(UniversiteRestController.class)
public class UniversiteRestControllerTest {



    private MockMvc mockMvc;

    static ObjectMapper objectMapper = new ObjectMapper();
    static ObjectWriter objectWriter = objectMapper.writer();
    public static String asJsonString(final Object obj) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Mock
    private IUniversiteService UniversiteService;

    @Mock
    private UniversiteRepository UniversiteRepository;

    @InjectMocks
    private UniversiteServiceImpl UniversiteServiceImpl;

    @InjectMocks
    private UniversiteRestController UniversiteRestController;

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
        this.mockMvc = MockMvcBuilders.standaloneSetup(UniversiteRestController).build();

    }

    @Test
    public void retrieveUniversitesTest() throws Exception {

        Mockito.when(UniversiteService.retrieveAllUniversites()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/universite/retrieve-all-universites")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$" , hasSize(3)))
                .andExpect(jsonPath("$[1].nomUniv" , is("ESPRITT")));
    }

    @Test
    public void createUniversiteTest() throws Exception {

        Mockito.when(UniversiteServiceImpl.addUniversite(universiteDTO)).thenReturn(universite);
        mockMvc.perform(MockMvcRequestBuilders.post("/universite/add-universite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(asJsonString(universiteDTO)))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    public void retrieveUniversiteTest() throws Exception {

        Mockito.when(UniversiteService.retrieveUniversite(Mockito.anyInt())).thenReturn(universite);
        mockMvc.perform(MockMvcRequestBuilders.get("/universite/retrieve-universite/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomUniv", is("ESPRIT")))
                .andReturn();
    }

    @Test
    public void updateUniversiteTest() throws Exception {

        Universite UniversiteUpdated = Universite.builder().idUniv(2).nomUniv("ESPRIT").build();
        Mockito.when(UniversiteService.updateUniversite(universite.getIdUniv(), universiteDTO)).thenReturn(UniversiteUpdated);

        mockMvc.perform(MockMvcRequestBuilders.put("/universite/update-universite/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(asJsonString(universiteDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.nomUniv", is("ESPRIT")))
                .andReturn();
    }

    @Test
    public void deleteUniversite() throws Exception {

        Mockito.when(UniversiteService.retrieveUniversite(Mockito.anyInt())).thenReturn(universite);

        mockMvc.perform(MockMvcRequestBuilders.delete("/universite/remove-universite/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }




}
