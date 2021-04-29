package com.starwars.demo.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.demo.DemoApplication;
import com.starwars.demo.dto.PlanetaDTO;
import com.starwars.demo.model.Planeta;
import com.starwars.demo.repository.PlanetaRepository;
import com.starwars.demo.service.PlanetaService;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanetaResource.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class PlanetaResourceTest {

    @Autowired
    private MockMvc mvc;


    @TestConfiguration
    static class PlanetaServiceTestContextConfiguration {

        @Bean
        public PlanetaService planetaService() {
            return new PlanetaService();
        }
    }


    @Autowired
    private PlanetaService planetaService;

    @MockBean
    private PlanetaRepository planetaRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void savePlaneta_thenStatus201()
            throws Exception {


        PlanetaDTO planetaDTO = new PlanetaDTO();
        planetaDTO.setNome("Hoth");
        planetaDTO.setClima("frozen");
        planetaDTO.setTerreno("tundra, ice caves, mountain ranges");
        planetaDTO.setQtdAparicoes(1);
        mvc.perform(MockMvcRequestBuilders
                .post("/api/planetas")
                .content(asJsonString(planetaDTO))
                .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void deletePlaneta_thenStatus200() throws Exception {
        /*
        {
          "_id": "608adf67b5fa424d90bed36f",
          "nome": "Tatooine",
          "clima": "arid",
          "terreno": "desert",
          "qtdAparicoes": 5
       }
         */
        mvc.perform( MockMvcRequestBuilders.delete("/api/planetas/{id}", "608adf67b5fa424d90bed36f") )
                .andExpect(status().isOk());
    }


    @Test
    public void getAllPlanetas() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/api/planetas")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
