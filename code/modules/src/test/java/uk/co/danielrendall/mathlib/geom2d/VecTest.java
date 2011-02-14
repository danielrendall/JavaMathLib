/*
 * Copyright (c) 2009, 2010 Daniel Rendall
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

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Daniel Rendall <drendall@gmail.com>
 * @created 04-Jun-2009 21:43:47
 */
public class VecTest {

    private final double DELTA = 10e-10;

    @Test
    public void testLength() {
        assertEquals(7.0d, new Vec(new Point(7, 0)).length(), DELTA);
        assertEquals(6.543d, new Vec(new Point(0, -6.543)).length(), DELTA);
        assertEquals(13.0d, new Vec(new Point(-5.0, 12.0)).length(), DELTA);
    }

    @Test
    public void testAdd() {
        Vec vec1 = new Vec(new Point(7, 0));
        Vec vec2 = new Vec(new Point(3, -6));
        assertEquals(new Vec(new Point(10, -6)), vec1.add(vec2));
    }

    @Test
    public void testNormalize() {
        Vec vec1 = new Vec(new Point(4, 4));
        Vec vec2 = vec1.normalize();
        assertEquals(1.0d, vec2.length(), DELTA);
        assertEquals(Math.PI / 4.0, vec2.angle(), DELTA);
    }

    @Test
    public void testDotProduct() {
        Vec vec1 = new Vec(new Point(7, 4));
        Vec vec2 = new Vec(new Point(2, 3));
        assertEquals(26.0d, vec1.dotProduct(vec2), DELTA);
        assertEquals(26.0d, vec2.dotProduct(vec1), DELTA);
    }

    @Test
    public void testDotProductAndNormalize() {
        Vec vec1 = new Vec(new Point(-3, 4));
        Vec vec2 = new Vec(new Point(2, 3));
        double angleBetween = vec1.angle() - vec2.angle();
        double cosAngle = Math.cos(angleBetween);
        assertEquals(cosAngle, vec1.normalize().dotProduct(vec2.normalize()), DELTA);
        assertEquals(cosAngle, vec2.normalize().dotProduct(vec1.normalize()), DELTA);

        assertEquals(vec1.length(), vec1.normalize().dotProduct(vec1), DELTA);
        assertEquals(vec2.length(), vec2.normalize().dotProduct(vec2), DELTA);
    }

    @Test @Ignore
    public void testApproximateLength() {
        final double increment = Math.PI / 180;
        Vec base = new Vec(100.0d, 0.0d);
        for (int i=0; i< 180; i++) {
            Vec test = base.rotate(increment * (double) i);
            double length = test.length();
            double approx = test.approximateLength();
            System.out.println("Angle: " + i + " length " + length + " approx " + approx + " diff " + (length - approx));
        }

    }

}
