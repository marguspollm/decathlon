package ee.margus.decathlon.service;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.entity.Event;
import ee.margus.decathlon.entity.Result;
import ee.margus.decathlon.dto.AthleteResult;
import ee.margus.decathlon.repository.ResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {
    @Mock
    private ResultRepository resultRepository;

    @Mock
    private EventService eventService;

    @Mock
    private AthleteService athleteService;

    @InjectMocks
    private ResultService resultService;

    @Test
    void getAthletesResults() {
    }

    @Test
    void whenGetAllAthletesResults_thenReturnAllAthletesList(){
        List<Result> results = new ArrayList<>();
        Result result = new Result();

        Event event = new Event(1L, "100m", true, 25.4347, 18.0, 1.81);

        List<Athlete> athletes = new ArrayList<>();
        Athlete athlete = new Athlete();
        athlete.setLastName("Test");
        athlete.setLastName("Tester");
        athlete.setCountry("UK");
        athlete.setAge(55);
        athlete.setId(1L);

        result.setAthlete(athlete);
        result.setResult(10);
        result.setEvent(event);
        result.setPoints(1097);
        results.add(result);

        athlete.setResults(results);
        athletes.add(athlete);

        List<AthleteResult> athleteResults = new ArrayList<>();
        AthleteResult athleteResult = new AthleteResult();
        athleteResult.setAthlete(athlete);
        athleteResult.setPoints(1097);
        athleteResult.setResults(results);
        athleteResults.add(athleteResult);

        when(athleteService.getAthletes()).thenReturn(athletes);
        when(resultRepository.findByAthlete_Id(1L)).thenReturn(results);
        when(athleteService.getAthlete(1L)).thenReturn(athlete);

        assertThat(resultService.getAthletesResults())
                .usingRecursiveComparison()
                .isEqualTo(athleteResults);
    }

    @Test
    void givenAthleteId_whenGetAthleteResults_thenReturnAthleteResults(){
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        Event event = new Event(1L, "100m", true, 25.4347, 18.0, 1.81);

        Athlete athlete = new Athlete();
        athlete.setLastName("Test");
        athlete.setLastName("Tester");
        athlete.setCountry("UK");
        athlete.setAge(55);
        athlete.setId(1L);

        result.setAthlete(athlete);
        result.setResult(10);
        result.setEvent(event);
        result.setPoints(1097);
        results.add(result);

        athlete.setResults(results);

        AthleteResult athleteResult = new AthleteResult();
        athleteResult.setAthlete(athlete);
        athleteResult.setPoints(1097);
        athleteResult.setResults(results);

        when(resultRepository.findByAthlete_Id(1L)).thenReturn(results);
        when(athleteService.getAthlete(1L)).thenReturn(athlete);

        //assertEquals(athleteResult, resultService.getAthleteResults(1L));
        assertThat(resultService.getAthleteResults(1L))
                .usingRecursiveComparison()
                .isEqualTo(athleteResult);
    }

    @Test
    void addResult() {
        Event event = new Event(1L, "100m", true, 25.4347, 18.0, 1.81);

        Athlete athlete = new Athlete();
        athlete.setLastName("Test");
        athlete.setLastName("Tester");
        athlete.setCountry("UK");
        athlete.setAge(55);
        athlete.setId(1L);

        Result result = new Result();
        result.setPoints(1097);
        result.setAthlete(athlete);
        result.setEvent(event);
        result.setResult(10);

        when(eventService.getEventById(1L)).thenReturn(event);
        when(resultRepository.findByAthlete_IdAndEvent_Id(1L, 1L)).thenReturn(Optional.empty());
        when(resultRepository.save(any(Result.class))).thenReturn(result);

        assertEquals(result, resultService.addResult(result));
    }
}