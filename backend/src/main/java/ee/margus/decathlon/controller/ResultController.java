package ee.margus.decathlon.controller;

import ee.margus.decathlon.entity.Result;
import ee.margus.decathlon.model.AthleteResult;
import ee.margus.decathlon.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;

    @GetMapping("results")
    public List<AthleteResult> getAthleteResults(){
        return resultService.getAthletesResults();
    }

    @GetMapping("results/athlete/{id}")
    public AthleteResult getAthleteResult(@PathVariable Long id){
        return resultService.getAthleteResults(id);
    }
    
    @PostMapping("results")
    public Result saveResult(@RequestBody Result result){
        return resultService.addResult(result);
    }
}
