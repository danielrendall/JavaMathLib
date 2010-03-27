package uk.co.danielrendall.fractdim.geom;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Daniel Rendall
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
