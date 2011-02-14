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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Daniel Rendall <drendall@gmail.com>
 * @created 27-Mar-2010 21:35:16
 */
public class PointTest {

    @Test
    public void testGetQuadrant() {
        assertEquals(Compass.NW, new Point(-3.0d,4.0d).getQuadrant());
        assertEquals(Compass.W, new Point(-3.0d,0.0d).getQuadrant());
        assertEquals(Compass.NE, new Point(2.0d,5.0d).getQuadrant());
        assertEquals(Compass.CENTER, new Point(0.0d,0.0d).getQuadrant());
        assertEquals(Compass.S, new Point(0.0d,-6.0d).getQuadrant());
        assertEquals(Compass.N, new Point(0.0d,7.5d).getQuadrant());
    }

    @Test
    public void testGetOctant() {
        assertEquals(Compass.NW, new Point(-3.0d,4.0d).getOctant());
        assertEquals(Compass.W, new Point(-30.0d,1.0d).getOctant());
        assertEquals(Compass.W, new Point(-30.0d,-1.0d).getOctant());
        assertEquals(Compass.NE, new Point(5.0d,5.0d).getOctant());
        assertEquals(Compass.CENTER, new Point(0.0d,0.0d).getOctant());
        assertEquals(Compass.S, new Point(0.5d,-6.0d).getOctant());
        assertEquals(Compass.N, new Point(-0.3d,7.5d).getOctant());
    }

}
