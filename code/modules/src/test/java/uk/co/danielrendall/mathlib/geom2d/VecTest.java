package uk.co.danielrendall.fractdim.geom;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Daniel Rendall
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

}
