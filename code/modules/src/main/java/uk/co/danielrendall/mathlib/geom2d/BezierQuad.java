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
 * @created 23-May-2009 18:20:30
 */
public final class BezierQuad implements ParametricCurve {

   private final Vec start, control, end;

   public BezierQuad(Point start, Point control, Point end) {
       this.start = new Vec(start);
       this.control = new Vec(control);
       this.end = new Vec(end);
   }

    public Point evaluate(double parameter) {
        final double oneMinus = 1.0d - parameter;
        Vec v0 = start.scale(oneMinus * oneMinus);
        Vec v1 = control.scale(2.0d * oneMinus * parameter);
        Vec v2 = end.scale(parameter * parameter);
        return Point.ORIGIN.displace(v0.add(v1).add(v2));
    }

    public BoundingBox getBoundingBox() {
        return BoundingBox.containing(new double[] { start.x(), control.x(), end.x()},
                new double[] { start.y(), control.y(), end.y()});
    }

    public String toString() {
        return String.format("BezierQuad { %s , %s , %s }", start, control, end);
    }

}
