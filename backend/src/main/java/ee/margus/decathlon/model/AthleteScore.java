package ee.margus.decathlon.model;

import ee.margus.decathlon.entity.Athlete;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AthleteScore {
    private Athlete athlete;
    private Integer points;
}
