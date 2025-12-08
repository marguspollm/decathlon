package ee.margus.decathlon.service;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.entity.Event;
import ee.margus.decathlon.entity.Score;
import ee.margus.decathlon.model.AthleteScore;
import ee.margus.decathlon.repository.ScoreRepository;
import ee.margus.decathlon.util.ScoreCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private AthleteService athleteService;

    public AthleteScore getAthleteScores(Long id){
        List<Score> athleteScore = scoreRepository.findByAthlete_Id(id);
        Athlete athlete = athleteService.getAthlete(id);
        int pointsSum = 0;

        for (Score s : athleteScore) {
            pointsSum += s.getPoints();
        }

        return new AthleteScore(athlete, pointsSum);
    }

    public Score saveScores(Score score){
        Event event = eventService.getEventById(score.getEvent().getId());
        Score saveScore = scoreRepository.findByAthlete_IdAndEvent_Id(score.getAthlete().getId(), score.getEvent().getId())
                .orElse(new Score());
        int calculatedScore = ScoreCalculator.calculate(
                score.getResult(),
                event.getIsTrack(),
                event.getParameterA(),
                event.getParameterB(),
                event.getParameterC()
                );

        saveScore.setPoints(calculatedScore);
        saveScore.setAthlete(score.getAthlete());
        saveScore.setResult(score.getResult());
        saveScore.setEvent(score.getEvent());

        scoreRepository.save(saveScore);
        return score;
    }
}
