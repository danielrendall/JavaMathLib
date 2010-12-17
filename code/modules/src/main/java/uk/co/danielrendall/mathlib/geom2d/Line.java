/*
 * Copyright (c) 2009, 2010 Daniel Rendall
 * This file is part of DReMathLib.
 *
 * DReMathLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DReMathLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DReMathLib.  If not, see <http://www.gnu.org/licenses/>
 */

package uk.co.danielrendall.mathlib.geom2d;

/**
 * @author Daniel Rendall <drendall@gmail.com>
 * @created 23-May-2009 11:07:08
 */
public final class Line implements ParametricCurve {

    private final Point start, end;
    private final Vec vec;

    public Line(Point end) {
        this(Point.ORIGIN, end);
    }

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        vec = new Vec(start, end);
    }

    public Line(Point start, Vec vec) {
        this(start, start.displace(vec));
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Vec getVec() {
        return vec;
    }

    public double length() {
        return vec.length();
    }

    public double lengthSquared() {
        return vec.lengthSquared();
    }

    public double approximateLength() {
        return vec.approximateLength();
    }


    public Point evaluate(double parameter) {
        return start.displace(vec.scale(parameter));
    }

    public BoundingBox getBoundingBox() {
        return BoundingBox.containing(new double[] { start.x(), end.x()},
                new double[] { start.y(), end.y()});
    }

    public String toString() {
        return String.format("Line { %s , %s }", start, end);
    }

    @Override
    public final boolean equals(Object obj) {
        return obj == this || (((Line) obj).start.equals(this.start) && ((Line) obj).end.equals(this.end));
    }

    @Override
    // @TODO - check this is reasonable
    public int hashCode() {
        return start.hashCode() ^ end.hashCode();
    }

}
