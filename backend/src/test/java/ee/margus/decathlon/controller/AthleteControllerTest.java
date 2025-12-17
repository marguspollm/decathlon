package ee.margus.decathlon.controller;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.service.AthleteService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AthleteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AthleteService athleteService;

    @Test
    void getAthletes() throws Exception {
        when(athleteService.getAthletes()).thenReturn(List.of());
        mockMvc.perform(get("/athletes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getAthlete() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Athlete athlete = new Athlete();
        athlete.setId(1L);
        athlete.setFirstName("Test");
        athlete.setAge(44);
        athlete.setCountry("UK");
        athlete.setLastName("Tester");

        when(athleteService.getAthlete(1L)).thenReturn(athlete);

        mockMvc.perform(get("/athletes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(athlete)));
    }

    @Test
    void saveAthlete() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setAge(44);
        athlete.setCountry("UK");
        athlete.setLastName("Tester");

        String athleteJSON = objectMapper.writeValueAsString(athlete);

        Athlete expectedAthlete = new Athlete();
        BeanUtils.copyProperties(athlete, expectedAthlete);
        expectedAthlete.setId(1L);
        String expectedJson = objectMapper.writeValueAsString(expectedAthlete);

        when(athleteService.saveAthlete(any(Athlete.class))).thenReturn(expectedAthlete);

        mockMvc.perform(post("/athletes")
                        .content(athleteJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
}