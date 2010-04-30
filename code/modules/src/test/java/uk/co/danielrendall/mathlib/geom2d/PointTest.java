package uk.co.danielrendall.mathlib.geom2d;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: daniel
 * Date: 27-Mar-2010
 * Time: 21:35:16
 * To change this template use File | Settings | File Templates.
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
