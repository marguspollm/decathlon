package ee.margus.decathlon.util;

import ee.margus.decathlon.entity.Event;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PointsCalculatorTest {
    private static final Map<String, Event> EVENT_CONSTANTS = Map.of(
            "100m",        new Event(1L, "100m", true, 25.4347, 18.0, 1.81),
            "LongJump",    new Event(2L, "long jump", false,0.14354, 220.0, 1.4),
            "ShotPut",     new Event(3L, "shot put", false, 51.39, 1.5, 1.05),
            "HighJump",    new Event(4L, "high jump", false, 0.8465, 75.0, 1.42),
            "400m",        new Event(5L, "400m", true, 1.53775, 82.0, 1.81),
            "110m", new Event(6L, "110m", true, 5.74352, 28.5, 1.92),
            "DiscusThrow", new Event(7L, "discus", false, 12.91, 4.0, 1.1),
            "PoleVault",   new Event(8L, "pole vault", false, 0.2797, 100.0, 1.35),
            "Javelin",new Event(9L, "javelin", false, 10.14, 7.0, 1.08),
            "1500m",       new Event(10L, "1500m", true, 0.03768, 480.0, 1.85)
    );

    @Test
    void given100mAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 10;
        Event event = EVENT_CONSTANTS.get("100m");
        assertEquals(1097,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }

    @Test
    void givenLongJumpAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 550;
        Event event = EVENT_CONSTANTS.get("LongJump");
        assertEquals(482,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }

    @Test
    void givenShotPutAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 15;
        Event event = EVENT_CONSTANTS.get("ShotPut");
        assertEquals(790,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }

    @Test
    void givenHighJumpAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 210;
        Event event = EVENT_CONSTANTS.get("HighJump");
        assertEquals(897,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }

    @Test
    void given400mAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 41;
        Event event = EVENT_CONSTANTS.get("400m");
        assertEquals(1277,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }

    @Test
    void given110mAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 15;
        Event event = EVENT_CONSTANTS.get("110m");
        assertEquals(850,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }

    @Test
    void givenDiscusThrowAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 56;
        Event event = EVENT_CONSTANTS.get("DiscusThrow");
        assertEquals(997,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }

    @Test
    void givenPoleVaultAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 250;
        Event event = EVENT_CONSTANTS.get("PoleVault");
        assertEquals(242,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }

    @Test
    void givenJavelinAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 62;
        Event event = EVENT_CONSTANTS.get("Javelin");
        assertEquals(768,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }

    @Test
    void given1500mAndResultIsValid_whenCalculatePoints_thenReturnValidPoints(){
        double result = 300;
        Event event = EVENT_CONSTANTS.get("1500m");
        assertEquals(560,
                PointsCalculator.calculate(
                        result,
                        event.getIsTrack(),
                        event.getParameterA(),
                        event.getParameterB(),
                        event.getParameterC()
                )
        );
    }
}