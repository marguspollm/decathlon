package ee.margus.decathlon.service;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.repository.AthleteRepository;
import ee.margus.decathlon.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {
    @Autowired
    private AthleteRepository athleteRepository;

    public List<Athlete> getAtheltes(){
        return athleteRepository.findAll();
    }

    public Athlete saveAthlete(Athlete athlete){
        Validator.validateAthlete(athlete);
        return athleteRepository.save(athlete);
    }

    public Athlete getAthlete(Long id){
        return athleteRepository.findById(id).orElseThrow();
    }

}
