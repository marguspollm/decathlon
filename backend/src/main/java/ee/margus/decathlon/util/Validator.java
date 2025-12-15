package ee.margus.decathlon.util;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.entity.Result;

public class Validator {

    public static void validateAthlete(Athlete athlete){
        if(athlete.getLastName() == null || athlete.getLastName().isBlank()) throw new RuntimeException("Last name can't be empty!");
        if(athlete.getFirstName() == null || athlete.getFirstName().isBlank()) throw new RuntimeException("First name can't be empty!");
        if(athlete.getCountry() == null || athlete.getCountry().isBlank()) throw new RuntimeException("Country can't be empty!");
        if(athlete.getAge() == null ) throw new RuntimeException("Age can't be empty");
        if(athlete.getAge() < 0 ) throw new RuntimeException("Age can't be below 0!");
    }

    public static void validateScore(Result result){
        if(result.getResult() == 0) throw new RuntimeException("Score can't be empty!");
        if(result.getResult() < 0) throw new RuntimeException("Score can't be lower then 0!");
    }
}
