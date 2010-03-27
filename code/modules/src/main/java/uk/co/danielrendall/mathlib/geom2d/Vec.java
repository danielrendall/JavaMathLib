package uk.co.danielrendall.fractdim.geom;

/**
 * @author Daniel Rendall
 * @created 23-May-2009 10:51:28
 */
public final class Vec implements XY {

    final Complex rep;

    private Vec(Complex c) {
        rep = c;
    }

    public Vec(double x, double y) {
        rep = new Complex(x, y);
    }

    public Vec(Point to) {
        rep = new Complex(to.rep.x, to.rep.y);
    }

    public Vec(Point from, Point to) {
        rep = new Complex(to.rep.x - from.rep.x, to.rep.y - from.rep.y);
    }

    public final double length() {
        return rep.mod();
    }

    public final double angle() {
        return rep.arg();
    }

    public final Vec add(Vec other) {
        return new Vec(rep.add(other.rep));
    }

    public final Vec sub(Vec other) {
        return new Vec(rep.sub(other.rep));
    }

    public final Vec neg() {
        return new Vec(rep.neg());
    }

    public final Vec rotate(double angle) {
        return new Vec(rep.rotate(angle));
    }

    public final Vec scale(double factor) {
        return new Vec(rep.times(factor));
    }

    public final Vec shrink(double factor) {
        if (factor == 0.0d) throw new IllegalArgumentException("Can't shrink by factor of zero");
        return scale(1.0d / factor);
    }

    public final Vec scaleAndRotate(double factor, double angle) {
        return new Vec(rep.times(Complex.modArg(factor, angle)));
    }

    public final Vec shrinkAndRotate(double factor, double angle) {
        return scaleAndRotate(1.0d / factor, angle);
    }

    public final String toString() {
        return (String.format("(%5.3f, %5.3f)", rep.x, rep.y));
    }

    public final double x() {
        return rep.x;
    }

    public final double y() {
        return rep.y;
    }

    public Vec normalize() {
        return new Vec(Complex.unit(rep.arg()));
    }

    public double dotProduct(Vec other) {
        return rep.x * other.rep.x + rep.y * other.rep.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vec vec = (Vec) o;

        if (!rep.equals(vec.rep)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return rep.hashCode();
    }
}
