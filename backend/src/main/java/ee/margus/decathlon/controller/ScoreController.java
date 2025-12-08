package ee.margus.decathlon.controller;

import ee.margus.decathlon.entity.Score;
import ee.margus.decathlon.model.AthleteScore;
import ee.margus.decathlon.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("scores/athlete/{id}")
    public AthleteScore getAthleteScore(@PathVariable Long id){
        return scoreService.getAthleteScores(id);
    }
    
    @PostMapping("scores")
    public Score saveScores(@RequestBody Score score){
        return scoreService.saveScores(score);
    }
}
