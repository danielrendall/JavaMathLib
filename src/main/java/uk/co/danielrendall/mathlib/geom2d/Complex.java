/*
 * Copyright (c) 2009, 2010, 2011 Daniel Rendall
 * This file is part of JavaMathLib.
 *
 * JavaMathLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JavaMathLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JavaMathLib.  If not, see <http://www.gnu.org/licenses/>
 */

package uk.co.danielrendall.mathlib.geom2d;

import uk.co.danielrendall.mathlib.util.Mathlib;

/**
 * @author Daniel Rendall <drendall@gmail.com>
 * @created 23-May-2009 10:05:23
 */
public final class Complex implements XY {

    private final static double EPSILON = Double.MIN_VALUE * 100;

    private final boolean xIsZero;
    private final boolean yIsZero;

    // make these package visible
    private final double x;
    private final double y;

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
        return xIsZero && yIsZero ? 0.0 : Mathlib.pythagorus(x, y);
    }

    public final double modSquared() {
        return xIsZero && yIsZero ? 0.0 : Mathlib.pythagorusSquared(x, y);
    }

    public final double arg() {
        if (xIsZero && yIsZero) {
            return 0.0d;
        } else {
            return Math.atan2(y, x);
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
