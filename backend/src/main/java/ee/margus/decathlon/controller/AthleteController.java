package ee.margus.decathlon.controller;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AthleteController {
    @Autowired
    private AthleteService athleteService;

    @GetMapping("athletes")
    public List<Athlete> getAthletes(){
        return athleteService.getAthletes();
    }

    @GetMapping("athletes/{id}")
    public Athlete getAthlete(@PathVariable Long id){
        return athleteService.getAthlete(id);
    }

    @PostMapping("athletes")
    public Athlete saveAthlete(@RequestBody Athlete athlete){
        if(athlete.getId() != null){
            throw new RuntimeException("Cannot save athlete with existing id!");
        }
        return athleteService.saveAthlete(athlete);
    }
}
