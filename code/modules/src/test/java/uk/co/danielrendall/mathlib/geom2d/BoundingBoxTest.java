package uk.co.danielrendall.mathlib.geom2d;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: daniel
 * Date: 24-Apr-2010
 * Time: 20:46:41
 * To change this template use File | Settings | File Templates.
 */
public class BoundingBoxTest {

    @Test
    public void testBoxFromNumbers() {
        BoundingBox bb1 = new BoundingBox(new double[]{10.1d, 0.02d, 5.3d, 5.5d}, new double[]{-3.0d, 5.23d, 1.2d, -10.4d});
        assertEquals(new BoundingBox(0.02d, 10.1d, -10.4d, 5.23d), bb1);
        BoundingBox bb2 = new BoundingBox(new double[]{-10.1d, -0.02d, -5.3d, -5.5d}, new double[]{-3.0d, -5.23d, -1.2d, -10.4d});
        assertEquals(new BoundingBox(-10.1d, -0.02d, -10.4d, -1.2d), bb2);
        BoundingBox bb3 = new BoundingBox(new double[]{-10.0d, -5.0d, 6.0d, 11.0d}, new double[]{12.0d, 7.0d, -8.0d, -13.0d});
        assertEquals(new BoundingBox(-10.0d, 11.0d, -13.0d, 12.0d), bb3);
    }

    @Test
    public void testExpandBoundingBox() {
        BoundingBox initial = new BoundingBox(-7.0d, -3.0d, -2.0d, 4.0d);
        assertEquals(new BoundingBox(-7.0d, -3.0d, -2.0d, 4.0d), initial);
        assertEquals(new BoundingBox(-7.0d, -2.0d, -2.0d, 4.0d), initial.expandToInclude(new BoundingBox(-6.0d, -2.0d, -1.0d, 3.0d)));
        assertEquals(new BoundingBox(-8.0d, -2.0d, -2.0d, 7.0d), initial.expandToInclude(new BoundingBox(-8.0d, -2.0d, 5.0d, 7.0d)));
        assertEquals(new BoundingBox(-7.0d, 14.0d, -2.0d, 11.0d), initial.expandToInclude(new BoundingBox(12.0d, 14.0d, 10.0d, 11.0d)));
    }

}
