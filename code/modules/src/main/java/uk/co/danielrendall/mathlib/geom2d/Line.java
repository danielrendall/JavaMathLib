package uk.co.danielrendall.fractdim.geom;

/**
 * @author Daniel Rendall
 * @created 23-May-2009 11:07:08
 */
public class Line implements ParametricCurve {

    private final Point start, end;
    private final Vec vec;

    public Line(Point end) {
        this(Point.ORIGIN, end);
    }

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        vec = new Vec(start, end);
    }

    public Line(Point start, Vec vec) {
        this(start, start.displace(vec));
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Vec getVec() {
        return vec;
    }

    public double length() {
        return vec.length();
    }

    public Point evaluate(double parameter) {
        return start.displace(vec.scale(parameter));
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox(new double[] { start.x(), end.x()},
                new double[] { start.y(), end.y()});
    }

    public String toString() {
        return String.format("Line { %s , %s }", start, end);
    }

    @Override
    public final boolean equals(Object obj) {
        return obj == this || (((Line) obj).start.equals(this.start) && ((Line) obj).end.equals(this.end));
    }

    @Override
    // @TODO - check this is reasonable
    public int hashCode() {
        return start.hashCode() ^ end.hashCode();
    }

}
