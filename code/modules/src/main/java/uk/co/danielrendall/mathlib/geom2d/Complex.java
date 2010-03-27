package uk.co.danielrendall.fractdim.geom;

import uk.co.danielrendall.fractdim.logging.Log;

/**
 * @author Daniel Rendall
 * @created 23-May-2009 10:05:23
 */
public final class Complex implements XY {

    private final static double EPSILON = Double.MIN_VALUE * 100;

    private final static double HALF_PI = Math.PI / 2.0d;

    private final boolean xIsZero;
    private final boolean yIsZero;

    // make these package visible
    final double x;
    final double y;

    public Complex(double x, double y) {
        this.x = x;
        this.y = y;
        xIsZero = Math.abs(x) < EPSILON;
        yIsZero = Math.abs(y) < EPSILON;
    }

    public static Complex unit() {
        return unit(1.0d);
    }

    public static Complex unit(double arg) {
        return modArg(1.0d, arg);
    }

    public static Complex modArg(double mod, double arg) {
        return new Complex (mod * Math.cos(arg), mod * Math.sin(arg));
    }

    public final Complex neg() {
        return new Complex(0.0d - x, 0.0d - y);
    }

    public final Complex add(Complex other) {
        return new Complex(x + other.x, y + other.y);
    }

    public final Complex sub(Complex other) {
        return add(other.neg());
    }

    public final double mod() {
        return xIsZero && yIsZero ? 0.0 : Math.sqrt((x * x) + (y * y));
    }

    public final double arg() {
        if (xIsZero) {
            // on the y axis
            if (yIsZero) {
                // the zero vector
                Log.misc.warn("Zero vector asked for argument");
                return 0.0;
            } else {
                // either straight up or straight down
                return (y > 0.0d) ? HALF_PI : -HALF_PI;
            }
        } else {
            if (yIsZero) {
                // on the x axis; either right or left
                return (x > 0) ? 0.0d : -Math.PI;
            } else {
                if (x >= 0.0d) {
                    return Math.atan(y/x);
                } else {
                    if (y >= 0.0d) {
                        // upper left quadrant
                        return Math.atan(y/x) + Math.PI;
                    } else {
                        // lower left quadrant
                        return Math.atan(y/x) - Math.PI;
                    }
                }
            }
        }
    }

    public final Complex times(double m) {
        return new Complex(x * m, y * m);
    }

    public final Complex times(Complex c) {
        // (x + iy)(a+ib) = xa - yb + (xb + ya)i
        return new Complex(x * c.x - y * c.y, x * c.y + y * c.x);
    }

    public final Complex rotate(double angle) {
        return times(Complex.unit(angle));
    }

    public final String toString() {
        return (String.format("(%s + %si)", x, y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complex complex = (Complex) o;

        if (Double.compare(complex.x, x) != 0) return false;
        if (Double.compare(complex.y, y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public final double x() {
        return x;
    }

    public final double y() {
        return y;
    }
}
