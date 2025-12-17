package ee.margus.decathlon.dto;

import ee.margus.decathlon.entity.Athlete;
import ee.margus.decathlon.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AthleteResult {
    private Athlete athlete;
    private Integer points;
    private List<Result> results;
}
