package uk.co.danielrendall.fractdim.geom;

/**
 * @author Daniel Rendall
 * @created 23-May-2009 18:38:47
 */
public interface ParametricCurve {

    public Point evaluate(double parameter);

    public BoundingBox getBoundingBox();

}
