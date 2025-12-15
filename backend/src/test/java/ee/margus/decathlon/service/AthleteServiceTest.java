package ee.margus.decathlon.service;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.entity.Event;
import ee.margus.decathlon.repository.AthleteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AthleteServiceTest {
    @Mock
    private AthleteRepository athleteRepository;

    @InjectMocks
    private AthleteService athleteService;

    @Test
    void whenGetAthletes_thenReturnAllAthletesList() {
        List<Athlete> athletes = new ArrayList<>();
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setLastName("Tester");
        athlete.setAge(10);
        athlete.setCountry("UK");
        athletes.add(athlete);

        when(athleteRepository.findAll()).thenReturn(athletes);

        assertEquals(athletes, athleteService.getAthletes());
    }

    @Test
    void givenExistingAthleteId_whenGetAthlete_thenReturnAthlete(){
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setLastName("Tester");
        athlete.setAge(10);
        athlete.setCountry("UK");
        athlete.setId(1L);

        when(athleteRepository.findById(1L)).thenReturn(Optional.of(athlete));

        assertEquals(athlete, athleteService.getAthlete(1L));
    }

    @Test
    void givenNotExistingAthleteId_whenGetAthlete_thenThrowException(){
        when(athleteRepository.findById(1L)).thenReturn(Optional.empty());
        String message = assertThrows(RuntimeException.class, () -> athleteService.getAthlete(1L)).getMessage();
        assertEquals("Athlete not found!", message);
    }

    @Test
    void givenCorrectAthlete_whenSaveAthlete_thenReturnSavedAthlete(){
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setLastName("Tester");
        athlete.setAge(10);
        athlete.setCountry("UK");

        when(athleteRepository.save(athlete)).thenReturn(athlete);

        assertEquals(athlete, athleteService.saveAthlete(athlete));
    }

    @Test
    void givenIncorrectAthleteWithoutLastname_whenSaveAthlete_thenThrowException(){
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setAge(10);
        athlete.setCountry("UK");

        String message = assertThrows(RuntimeException.class, () -> athleteService.saveAthlete(athlete)).getMessage();

        assertEquals("Last name can't be empty!", message);
    }

    @Test
    void givenIncorrectAthleteWithEmptyLastname_whenSaveAthlete_thenThrowException(){
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setLastName("");
        athlete.setAge(10);
        athlete.setCountry("UK");

        String message = assertThrows(RuntimeException.class, () -> athleteService.saveAthlete(athlete)).getMessage();

        assertEquals("Last name can't be empty!", message);
    }

    @Test
    void givenIncorrectAthleteWithoutFirstname_whenSaveAthlete_thenThrowException(){
        Athlete athlete = new Athlete();
        athlete.setLastName("Test");
        athlete.setAge(10);
        athlete.setCountry("UK");

        String message = assertThrows(RuntimeException.class, () -> athleteService.saveAthlete(athlete)).getMessage();

        assertEquals("First name can't be empty!", message);
    }

    @Test
    void givenIncorrectAthleteWithEmptyFirstname_whenSaveAthlete_thenThrowException(){
        Athlete athlete = new Athlete();
        athlete.setFirstName("");
        athlete.setLastName("Test");
        athlete.setAge(10);
        athlete.setCountry("UK");

        String message = assertThrows(RuntimeException.class, () -> athleteService.saveAthlete(athlete)).getMessage();

        assertEquals("First name can't be empty!", message);
    }

    @Test
    void givenIncorrectAthleteWithoutCountry_whenSaveAthlete_thenThrowException(){
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setLastName("Tester");
        athlete.setAge(10);

        String message = assertThrows(RuntimeException.class, () -> athleteService.saveAthlete(athlete)).getMessage();

        assertEquals("Country can't be empty!", message);
    }

    @Test
    void givenIncorrectAthleteWithEmptyCountry_whenSaveAthlete_thenThrowException(){
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setLastName("Tester");
        athlete.setAge(10);
        athlete.setCountry("");

        String message = assertThrows(RuntimeException.class, () -> athleteService.saveAthlete(athlete)).getMessage();

        assertEquals("Country can't be empty!", message);
    }

    @Test
    void givenIncorrectAthleteWithoutAge_whenSaveAthlete_thenThrowException(){
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setLastName("Tester");
        athlete.setCountry("UK");

        String message = assertThrows(RuntimeException.class, () -> athleteService.saveAthlete(athlete)).getMessage();

        assertEquals("Age can't be empty", message);
    }

    @Test
    void givenIncorrectAthleteWithAgeBelowZero_whenSaveAthlete_thenThrowException(){
        Athlete athlete = new Athlete();
        athlete.setFirstName("Test");
        athlete.setLastName("Tester");
        athlete.setAge(-9);
        athlete.setCountry("UK");

        String message = assertThrows(RuntimeException.class, () -> athleteService.saveAthlete(athlete)).getMessage();

        assertEquals("Age can't be below 0!", message);
    }

}