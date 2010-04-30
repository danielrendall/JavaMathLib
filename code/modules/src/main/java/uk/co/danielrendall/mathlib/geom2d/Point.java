package uk.co.danielrendall.mathlib.geom2d;

/**
 * @author Daniel Rendall
 * @created 23-May-2009 11:08:18
 */
public final class Point implements XY {

    private final double OCT1 = -7.0d * Math.PI / 8.0d;
    private final double OCT2 = -5.0d * Math.PI / 8.0d;
    private final double OCT3 = -3.0d * Math.PI / 8.0d;
    private final double OCT4 = -1.0d * Math.PI / 8.0d;
    private final double OCT5 = 1.0d * Math.PI / 8.0d;
    private final double OCT6 = 3.0d * Math.PI / 8.0d;
    private final double OCT7 = 5.0d * Math.PI / 8.0d;
    private final double OCT8 = 7.0d * Math.PI / 8.0d;

    private final double QUAD1 = -Math.PI / 2.0d;
    private final double QUAD2 = 0;
    private final double QUAD3 = Math.PI / 2.0d;
    private final double QUAD4 = Math.PI;

    public final static Point ORIGIN = new Point(0.0d, 0.0d);

    // rep is package visible
    private final Complex rep;

    private Point(Complex c) {
        rep = c;
    }

    public Point(double x, double y) {
        rep = new Complex(x, y);
    }

    public final Point displace(Vec vec) {
        return new Point(rep.add(vec.rep()));
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
        return rep.x();
    }

    public final double y() {
        return rep.y();
    }

    public final String toString() {
        return (String.format("(%s, %s)", rep.x(), rep.y()));
    }

    public final double distanceTo(Point other) {
        return line(other).length();
    }

    public double squaredDistanceTo(Point other) {
        return line(other).lengthSquared();
    }

    public double approximateDistanceTo(Point other) {
        return line(other).approximateLength();
    }

    final Complex rep() {
        return rep;
    }

    @Override
    public final boolean equals(Object obj) {
        return obj == this || ((Point) obj).rep.equals(this.rep);
    }

    @Override
    public int hashCode() {
        return rep.hashCode();
    }

    public boolean isOrigin() {
        return ORIGIN.equals(this);
    }

    public Compass getOctant() {
        if (isOrigin()) return Compass.CENTER;
        double angle = rep.arg();
        if (angle < OCT1) return Compass.W;
        if (angle < OCT2) return Compass.SW;
        if (angle < OCT3) return Compass.S;
        if (angle < OCT4) return Compass.SE;
        if (angle < OCT5) return Compass.E;
        if (angle < OCT6) return Compass.NE;
        if (angle < OCT7) return Compass.N;
        if (angle < OCT8) return Compass.NW;
        return Compass.W;
    }

    public Compass getQuadrant() {
        if (isOrigin()) return Compass.CENTER;
        if (rep.x() == 0.0d) {
            return (rep.y() > 0.0d) ? Compass.N : Compass.S;
        } else if (rep.y() == 0.0d) {
            return (rep.x() > 0.0d) ? Compass.E : Compass.W;
        }
        double angle = rep.arg();
        if (angle < QUAD1) return Compass.SW;
        if (angle < QUAD2) return Compass.SE;
        if (angle < QUAD3) return Compass.NE;
        return Compass.NW;
    }

}
