package uk.co.danielrendall.fractdim.geom;

/**
 * @author Daniel Rendall
 * @created 23-May-2009 18:21:39
 */
public class BezierCubic implements ParametricCurve {

    private final Vec start, control1, control2, end;

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
        return new BoundingBox(new double[] { start.x(), control1.x(), control2.x(), end.x()},
                new double[] { start.y(), control1.y(), control2.y(), end.y()});
    }

    public String toString() {
        return String.format("BezierCubic { %s , %s , %s , %s }", start, control1, control2, end);
    }

}
