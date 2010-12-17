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

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Daniel Rendall <drendall@gmail.com>
 * @created 23-May-2009 10:48:51
 */
public class ComplexTest {

    private final double DELTA = 10e-10;


    @Test
    public void testComplex() {
        Complex a = new Complex(4.5, 0.2);
        assertEquals(4.5, a.x(), DELTA);
        assertEquals(0.2, a.y(), DELTA);
    }

    @Test
    public void testModArg() {
        Complex a = new Complex(4.5, 0.2);
        assertEquals(Math.sqrt(4.5*4.5 + 0.2*0.2), a.mod(), DELTA);
        assertEquals(Math.atan(0.2/4.5), a.arg(), DELTA);
    }

    @Test
    public void testQuadrants() {
        Complex TL = new Complex(-2.0, 2.0);
        Complex TR = new Complex(3.0, 3.0);
        Complex BL = new Complex(-1.5, -1.5);
        Complex BR = new Complex(4.567, -4.567);
        assertEquals(3.0 * Math.PI / 4.0, TL.arg(), DELTA);
        assertEquals(Math.PI / 4.0, TR.arg(), DELTA);
        assertEquals(- 3.0 * Math.PI / 4.0, BL.arg(), DELTA);
        assertEquals(- Math.PI / 4.0, BR.arg(), DELTA);
    }

    @Test
    public void testAdd() {
        Complex a = new Complex(4.5, 0.2);
        Complex b = new Complex(2.1, 9.4);
        Complex c = a.add(b);
        assertEquals(6.6, c.x(), DELTA);
        assertEquals(9.6, c.y(), DELTA);
    }

    @Test
    public void testSub() {
        Complex a = new Complex(4.5, 0.2);
        Complex b = new Complex(2.1, 9.4);
        Complex c = a.sub(b);
        assertEquals(2.4, c.x(), DELTA);
        assertEquals(-9.2, c.y(), DELTA);
    }

    @Test
    public void testScale() {
        Complex a = new Complex(4.5, 0.2);
        Complex b = a.times(2.5);

        assertEquals(11.25, b.x(), DELTA);
        assertEquals(0.5, b.y(), DELTA);
    }

    @Test
    public void testTimes() {
        Complex a = new Complex(4.5, 0.2);
        Complex b = new Complex(2.1, 9.4);
        Complex c = a.times(b);

        assertEquals(a.mod() * b.mod(), c.mod(), DELTA);
        assertEquals(a.arg() + b.arg(), c.arg(), DELTA);
    }


}
