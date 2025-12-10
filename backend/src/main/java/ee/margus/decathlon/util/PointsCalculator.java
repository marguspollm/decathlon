package ee.margus.decathlon.util;

public class PointsCalculator {

    public static int calculate(double result,
                                boolean isTrackEvent,
                                double a,
                                double b,
                                double c)
    {
        if(isTrackEvent){
            return Math.toIntExact(Math.round(a * Math.pow((b - result), c)));
        } else {
            return Math.toIntExact(Math.round(a * Math.pow((result - b),c)));
        }
    }
}
