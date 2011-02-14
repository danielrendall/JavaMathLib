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
 * @created 23-May-2009 10:51:28
 */
public final class Vec implements XY {

    private final Complex rep;

    public final static Vec ZERO = new Vec(0.0d, 0.0d);

    public final static Vec I = new Vec(1.0d, 0.0d);
    public final static Vec J = new Vec(0.0d, 1.0d);

    public static Vec unit(double angle) {
        return new Vec(Complex.unit(angle));
    }

    private Vec(Complex c) {
        rep = c;
    }

    public Vec(double x, double y) {
        rep = new Complex(x, y);
    }

    public Vec(Point to) {
        rep = to.rep();
    }

    public Vec(Point from, Point to) {
        rep = to.rep().sub(from.rep());
    }

    public final double length() {
        return rep.mod();
    }

    public double lengthSquared() {
        return rep.modSquared();
    }

    public double approximateLength() {
        final double absX = Math.abs(x());
        final double absY = Math.abs(y());
        final double sum = absX + absY;
        if (sum == 0.0d) {
            return 0.0d;
        } else {
            final double difference = Math.abs(absX - absY);
            final double fractionalDifference = difference / sum;
            //fd = 0 = no difference = multiply sum by 1/sqrt(2)
            //fd = 1 = difference = sum = multiply sum by 1

            final double factor = (1 - Mathlib.SQRT_TWO_RECIPROCAL) * fractionalDifference + Mathlib.SQRT_TWO_RECIPROCAL;
            return sum * factor;
        }
    }

    public final double angle() {
        return rep.arg();
    }

    public final Vec add(Vec other) {
        return new Vec(rep.add(other.rep));
    }

    public final Vec sub(Vec other) {
        return new Vec(rep.sub(other.rep));
    }

    public final Vec neg() {
        return new Vec(rep.neg());
    }

    public final Vec rotate(double angle) {
        return new Vec(rep.rotate(angle));
    }

    public final Vec scale(double factor) {
        return new Vec(rep.times(factor));
    }

    public final Vec shrink(double factor) {
        if (factor == 0.0d) throw new IllegalArgumentException("Can't shrink by factor of zero");
        return scale(1.0d / factor);
    }

    public final Vec scaleAndRotate(double factor, double angle) {
        return new Vec(rep.times(Complex.modArg(factor, angle)));
    }

    public final Vec shrinkAndRotate(double factor, double angle) {
        return scaleAndRotate(1.0d / factor, angle);
    }

    public final String toString() {
        return toString(5,3);
    }

    public final String toString(int whole, int decimal) {
        final String format = "%" + whole + "." + decimal + "f";
        return (String.format("(" + format + ", " + format + ")", rep.x(), rep.y()));
    }

    public final double x() {
        return rep.x();
    }

    public final double y() {
        return rep.y();
    }

    public Vec normalize() {
        return new Vec(Complex.unit(rep.arg()));
    }

    public double dotProduct(Vec other) {
        return x() * other.x() + y() * other.y();
    }

    final Complex rep() {
        return rep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vec vec = (Vec) o;

        if (!rep.equals(vec.rep)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return rep.hashCode();
    }
}
