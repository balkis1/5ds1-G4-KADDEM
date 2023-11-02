package tn.esprit.spring.kaddem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import tn.esprit.spring.kaddem.controllers.DetailEquipeRestController;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.DetailEquipeDTO;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.services.DetailEquipeServiceImpl;
import tn.esprit.spring.kaddem.services.IDetailEquipeService;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(DetailEquipeRestController.class)
public class DetailEquipeRestControllerTest {



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
    private IDetailEquipeService detailEquipeService;

    @Mock
    private DetailEquipeRepository detailEquipeRepository;

    @InjectMocks
    private DetailEquipeServiceImpl detailEquipeServiceImpl;

    @InjectMocks
    private DetailEquipeRestController detailEquipeRestController;

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
        this.mockMvc = MockMvcBuilders.standaloneSetup(detailEquipeRestController).build();

    }

    @Test
    public void retrieveDetailEquipesTest() throws Exception {

        Mockito.when(detailEquipeService.retrieveAllDetailEquipes()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/DetailEquipe/retrieve-all-DetailEquipes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$" , hasSize(3)))
                .andExpect(jsonPath("$[2].thematique" , is("thematique3")));
    }

    @Test
    public void createDetailEquipeTest() throws Exception {

        Mockito.when(detailEquipeServiceImpl.addDetailEquipe(detailEquipeDTO)).thenReturn(detailEquipe);
        mockMvc.perform(MockMvcRequestBuilders.post("/DetailEquipe/add-DetailEquipe")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(asJsonString(detailEquipeDTO)))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    public void retrieveDetailEquipeTest() throws Exception {

        Mockito.when(detailEquipeService.retrieveDetailEquipe(Mockito.anyInt())).thenReturn(detailEquipe);
        mockMvc.perform(MockMvcRequestBuilders.get("/DetailEquipe/retrieve-DetailEquipe/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.thematique", is("thematique1")))
                .andReturn();
    }

    @Test
    public void updateDetailEquipeTest() throws Exception {

        DetailEquipe detailEquipeUpdated = DetailEquipe.builder().idDetailEquipe(2).salle(1).thematique("thematique1").build();
        Mockito.when(detailEquipeService.updateDetailEquipe(detailEquipe.getIdDetailEquipe(), detailEquipeDTO)).thenReturn(detailEquipeUpdated);

        mockMvc.perform(MockMvcRequestBuilders.put("/DetailEquipe/update-DetailEquipe/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(asJsonString(detailEquipeDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.thematique", is("thematique1")))
                .andReturn();
    }

    @Test
    public void deleteDetailEquipe() throws Exception {

        Mockito.when(detailEquipeService.retrieveDetailEquipe(Mockito.anyInt())).thenReturn(detailEquipe);

        mockMvc.perform(MockMvcRequestBuilders.delete("/DetailEquipe/remove-DetailEquipe/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }




}
