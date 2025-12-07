package ee.margus.decathlon.service;

import ee.margus.decathlon.entity.Event;
import ee.margus.decathlon.entity.Score;
import ee.margus.decathlon.model.AthleteScore;
import ee.margus.decathlon.repository.EventRepository;
import ee.margus.decathlon.repository.ScoreRepository;
import ee.margus.decathlon.util.ScoreCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private EventRepository eventRepository;

    public List<AthleteScore> getAllAthleteScores() {
        List<Score> scores = scoreRepository.findAll();
        Map<Long, AthleteScore> athletePoints = new HashMap<>();

        for (Score s : scores) {
            athletePoints.merge(
                    s.getAthlete().getId(),
                    new AthleteScore(s.getAthlete(), s.getPoints()),
                    (oldVal, newVal) ->
                            new AthleteScore(oldVal.getAthlete(), oldVal.getPoints() + newVal.getPoints())
            );
        }

        return athletePoints.values().stream().toList();
    }

    public List<Score> getAthleteScores(Long id){
        return scoreRepository.findByAthlete_Id(id);
    }

    public Score saveScores(Score score){
        Event event = eventRepository.findById(score.getEvent().getId())
                .orElseThrow(() -> new RuntimeException("Event id doesn't exist!"));
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
