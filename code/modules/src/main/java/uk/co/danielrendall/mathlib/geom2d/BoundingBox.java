package uk.co.danielrendall.fractdim.geom;

/**
 * Created by IntelliJ IDEA.
* User: daniel
* Date: 27-Jun-2009
* Time: 16:41:14
* To change this template use File | Settings | File Templates.
*/
public class BoundingBox {
    private final double minX, maxX, minY, maxY;

    public BoundingBox() {
        this(Double.MAX_VALUE, Double.MIN_VALUE, Double.MAX_VALUE, Double.MIN_VALUE);
    }

    public BoundingBox(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public BoundingBox(double[] xValues, double[] yValues) {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (int i=0; i < xValues.length; i++) {
            minX = Math.min(minX, xValues[i]);
            maxX = Math.max(maxX, xValues[i]);
            minY = Math.min(minY, yValues[i]);
            maxY = Math.max(maxY, yValues[i]);
        }
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public BoundingBox expandToInclude(BoundingBox other) {
        boolean isIncluded = true;
        double newMinX = minX;
        double newMaxX = maxX;
        double newMinY = minY;
        double newMaxY = maxY;

        if (other.minX < minX) {
            isIncluded = false;
            newMinX = other.minX;
        }
        if (other.maxX > maxX) {
            isIncluded = false;
            newMaxX = other.maxX;
        }
        if (other.minY < minY) {
            isIncluded = false;
            newMinY = other.minY;
        }
        if (other.maxY > maxY) {
            isIncluded = false;
            newMaxY = other.maxY;
        }
        return isIncluded ? this : new BoundingBox(newMinX, newMaxX, newMinY, newMaxY);
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getWidth() {
        return maxX - minX;
    }

    public double getHeight() {
        return maxY - minY;
    }

    @Override
    public String toString() {
        return String.format("{minX=%5.3f, maxX=%5.3f, minY=%5.3f, maxY=%5.3f, width=%5.3f, height=%5.3f'}'", minX, maxX, minY, maxY, getWidth(), getHeight());
    }
}
