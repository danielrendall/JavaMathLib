package uk.co.danielrendall.mathlib.util;

/**
 * Created by IntelliJ IDEA.
 * User: daniel
 * Date: 30-Apr-2010
 * Time: 19:38:47
 * To change this template use File | Settings | File Templates.
 */
public final class Mathlib {

    public final static double TWO_PI = 2.0d * Math.PI;
    public final static double PI_BY_TWO = Math.PI / 2.0d;
    public final static double THREE_PI_BY_TWO = 3.0d * Math.PI / 2.0d;
    public final static double PI_BY_FOUR = Math.PI / 4.0d;
    public final static double THREE_PI_BY_FOUR = 3.0d * Math.PI / 4.0d;

    public final static double SQRT_TWO = Math.sqrt(2.0d);
    public final static double SQRT_TWO_RECIPROCAL = 1.0d / SQRT_TWO;

    public final static double SQRT_THREE = Math.sqrt(3.0d);
    public final static double SQRT_THREE_RECIPROCAL = 1.0d / SQRT_THREE;

    public final static double SQRT_FIVE = Math.sqrt(5.0d);
    public final static double SQRT_FIVE_RECIRPOCAL = 1.0d / SQRT_FIVE;

    public final static double PHI = (SQRT_FIVE + 1.0d) / 2.0d;
    public final static double PHI_RECIPROCAL = 1.0d / PHI;

    public static double mean(double d1, double d2) {
        if (d1 <= d2) {
            return d1 + (d2 - d1)/2.0d;
        } else {
            return mean(d2, d1);
        }
    }

    public static double pythagorus(double x, double y) {
        return Math.sqrt(pythagorusSquared(x, y));
    }
    
    public static double pythagorusSquared(double x, double y) {
        return (x * x) + (y * y);
    }
}
