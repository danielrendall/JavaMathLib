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

/**
 * @author Daniel Rendall <drendall@gmail.com>
 * @created 23-May-2009 18:21:39
 */
public final class BezierCubic implements uk.co.danielrendall.mathlib.geom2d.ParametricCurve {

    private final uk.co.danielrendall.mathlib.geom2d.Vec start, control1, control2, end;

    public BezierCubic(Point start, Point control1, Point control2, Point end) {
        this.start = new Vec(start);
        this.control1 = new Vec(control1);
        this.control2 = new Vec(control2);
        this.end = new Vec(end);
    }

    public Point evaluate(double parameter) {
        final double oneMinus = 1.0d - parameter;
        Vec v0 = start.scale(oneMinus * oneMinus * oneMinus);
        Vec v1 = control1.scale(3.0d * oneMinus * oneMinus * parameter);
        Vec v2 = control2.scale(3.0d * oneMinus * parameter * parameter);
        Vec v3 = end.scale(parameter * parameter * parameter);
        return Point.ORIGIN.displace(v0.add(v1).add(v2).add(v3));
    }

    public BoundingBox getBoundingBox() {
        return BoundingBox.containing(new double[] { start.x(), control1.x(), control2.x(), end.x()},
                new double[] { start.y(), control1.y(), control2.y(), end.y()});
    }

    public String toString() {
        return String.format("BezierCubic { %s , %s , %s , %s }", start, control1, control2, end);
    }

}
