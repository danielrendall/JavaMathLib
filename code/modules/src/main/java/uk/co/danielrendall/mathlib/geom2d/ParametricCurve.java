package uk.co.danielrendall.mathlib.geom2d;

/**
 * @author Daniel Rendall
 * @created 23-May-2009 18:38:47
 */
public interface ParametricCurve {

    public Point evaluate(double parameter);

    public BoundingBox getBoundingBox();

}
