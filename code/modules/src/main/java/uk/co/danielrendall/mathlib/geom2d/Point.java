package uk.co.danielrendall.fractdim.geom;

/**
 * @author Daniel Rendall
 * @created 23-May-2009 11:08:18
 */
public class Point implements XY {

    public final static Point ORIGIN = new Point(0.0d, 0.0d);

    // rep is package visible
    final Complex rep;

    private Point(Complex c) {
        rep = c;
    }

    public Point(double x, double y) {
        rep = new Complex(x, y);
    }

    public final Point displace(Vec vec) {
        return new Point(rep.x + vec.rep.x, rep.y + vec.rep.y);
    }

    public final Point rotate(double angle) {
        return new Point(rep.rotate(angle));
    }

    public final Line line(Point other) {
        return new Line(this, other);
    }

    public final Line line(Vec vec) {
        return new Line(this, vec);
    }

    public final double x() {
        return rep.x;
    }

    public final double y() {
        return rep.y;
    }

    public final String toString() {
        return (String.format("(%s, %s)", rep.x, rep.y));
    }

    @Override
    public final boolean equals(Object obj) {
        return obj == this || ((Point) obj).rep.equals(this.rep);
    }

    @Override
    public int hashCode() {
        return rep.hashCode();
    }
}
