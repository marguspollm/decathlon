package ee.margus.decathlon.service;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.entity.Event;
import ee.margus.decathlon.entity.Result;
import ee.margus.decathlon.model.AthleteResult;
import ee.margus.decathlon.repository.ResultRepository;
import ee.margus.decathlon.util.PointsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private AthleteService athleteService;

    public List<AthleteResult> getAthletesResults(){
        List<Athlete> athletes = athleteService.getAthletes();
        return athletes.stream().map( ath -> getAthleteResults(ath.getId())).toList();
    }

    public AthleteResult getAthleteResults(Long id){
        List<Result> athleteResult = resultRepository.findByAthlete_Id(id);
        Athlete athlete = athleteService.getAthlete(id);
        int pointsSum = 0;

        for (Result s : athleteResult) {
            pointsSum += s.getPoints();
        }

        return new AthleteResult(athlete, pointsSum, athleteResult);
    }

    public Result addResult(Result result){
        Event event = eventService.getEventById(result.getEvent().getId());
        Result saveResult = resultRepository.findByAthlete_IdAndEvent_Id(result.getAthlete().getId(), result.getEvent().getId())
                .orElse(new Result());
        int calculatedScore = PointsCalculator.calculate(
                result.getResult(),
                event.getIsTrack(),
                event.getParameterA(),
                event.getParameterB(),
                event.getParameterC()
                );

        saveResult.setPoints(calculatedScore);
        saveResult.setAthlete(result.getAthlete());
        saveResult.setResult(result.getResult());
        saveResult.setEvent(result.getEvent());

        return resultRepository.save(saveResult);
    }
}
