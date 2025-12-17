package ee.margus.decathlon.controller;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.entity.Event;
import ee.margus.decathlon.entity.Result;
import ee.margus.decathlon.dto.AthleteResult;
import ee.margus.decathlon.service.ResultService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ResultService resultService;

    @Test
    void getAthleteResults() throws Exception {
        when(resultService.getAthletesResults()).thenReturn(List.of());
        mockMvc.perform(get("/results"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getAthleteResult() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AthleteResult athleteResult = new AthleteResult();
        Athlete athlete = new Athlete();
        Result result = new Result();
        athleteResult.setAthlete(athlete);
        athleteResult.setPoints(1500);
        athleteResult.setResults(List.of(result));

        when(resultService.getAthleteResults(1L)).thenReturn(athleteResult);

        mockMvc.perform(get("/results/athlete/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(athleteResult)));
    }

    @Test
    void saveResult() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = new Result();
        Athlete athlete = new Athlete();
        athlete.setId(1L);
        Event event = new Event();
        event.setId(1L);

        result.setResult(10);
        result.setAthlete(athlete);
        result.setEvent(event);

        when(resultService.addResult(any(Result.class))).thenReturn(result);

        mockMvc.perform(post("/results")
                        .content(objectMapper.writeValueAsString(result))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(result)));
    }
}