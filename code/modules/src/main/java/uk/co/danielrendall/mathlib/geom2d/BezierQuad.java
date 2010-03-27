package uk.co.danielrendall.fractdim.geom;

/**
 * @author Daniel Rendall
 * @created 23-May-2009 18:20:30
 */
public class BezierQuad implements ParametricCurve {

   private final Vec start, control, end;

   public BezierQuad(Point start, Point control, Point end) {
       this.start = new Vec(start);
       this.control = new Vec(control);
       this.end = new Vec(end);
   }

    public Point evaluate(double parameter) {
        final double oneMinus = 1.0d - parameter;
        Vec v0 = start.scale(oneMinus * oneMinus);
        Vec v1 = control.scale(2.0d * oneMinus * parameter);
        Vec v2 = end.scale(parameter * parameter);
        return Point.ORIGIN.displace(v0.add(v1).add(v2));
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox(new double[] { start.x(), control.x(), end.x()},
                new double[] { start.y(), control.y(), end.y()});
    }

    public String toString() {
        return String.format("BezierQuad { %s , %s , %s }", start, control, end);
    }

}
