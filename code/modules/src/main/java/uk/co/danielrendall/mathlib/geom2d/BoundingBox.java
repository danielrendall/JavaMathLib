package uk.co.danielrendall.mathlib.geom2d;

import uk.co.danielrendall.mathlib.util.Mathlib;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by IntelliJ IDEA.
* User: daniel
* Date: 27-Jun-2009
* Time: 16:41:14
* To change this template use File | Settings | File Templates.
*/
public final class BoundingBox {
    private final double minX, maxX, minY, maxY;
    private final Point center;

    public static BoundingBox empty() {
        return new BoundingBox(Double.MAX_VALUE, -Double.MAX_VALUE, Double.MAX_VALUE, -Double.MAX_VALUE, false);
    }

    public static BoundingBox containing(Point... points) {
        double minX = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;
        for (Point p : points) {
            minX = Math.min(minX, p.x());
            maxX = Math.max(maxX, p.x());
            minY = Math.min(minY, p.y());
            maxY = Math.max(maxY, p.y());
        }
        return new BoundingBox(minX, maxX, minY, maxY);
    }

    public static BoundingBox containing(double[] xValues, double[] yValues) {
        double minX = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;
        for (double xValue : xValues) {
            minX = Math.min(minX, xValue);
            maxX = Math.max(maxX, xValue);
        }
        for (double yValue : yValues) {
            minY = Math.min(minY, yValue);
            maxY = Math.max(maxY, yValue);
        }
        return new BoundingBox(minX, maxX, minY, maxY);
    }

    public BoundingBox(double minX, double maxX, double minY, double maxY) {
        this(minX, maxX, minY, maxY, true);
    }

    // Private constructor used to create 'empty' box which contains nothing and will expand correctly
    // if 'expandToInclude' is used
    private BoundingBox(double minX, double maxX, double minY, double maxY, boolean checkSanity) {
        if (checkSanity && (minX > maxX || minY > maxY)) throw new IllegalArgumentException(String.format("Bad bounds: %s, %s, %s, %s", minX, maxX, minY, maxY));
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.center = new Point(Mathlib.mean(minX, maxX), Mathlib.mean(minY, maxY));
    }

    public final BoundingBox expandToInclude(BoundingBox other) {
        if (contains(other)) return this;
        if (isContainedBy(other)) return other;

        double newMinX = minX;
        double newMaxX = maxX;
        double newMinY = minY;
        double newMaxY = maxY;

        if (other.minX < minX) {
            newMinX = other.minX;
        }
        if (other.maxX > maxX) {
            newMaxX = other.maxX;
        }
        if (other.minY < minY) {
            newMinY = other.minY;
        }
        if (other.maxY > maxY) {
            newMaxY = other.maxY;
        }
        return new BoundingBox(newMinX, newMaxX, newMinY, newMaxY);
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

    public boolean contains(BoundingBox other) {
        return minX < other.minX && maxX > other.maxX && minY < other.minY && maxY > other.maxY;
    }

    public boolean isContainedBy(BoundingBox other) {
        return other.contains(this);
    }

    public boolean isOutside(BoundingBox other) {
        return minX > other.maxX || maxX < other.minX || minY > other.maxY || maxY < other.minY;
    }

    public boolean intersects(BoundingBox other) {
        return !contains(other) && !isContainedBy(other) && !isOutside(other);
    }
    
    public Point randomPoint() {
        double x = Math.random() * getWidth() + minX;
        double y = Math.random() * getHeight() + minY;
        return new Point(x, y);
    }

    public Point getCenter() {
        return center;
    }

    public double centerDistanceTo(BoundingBox other) {
        return center.distanceTo(other.getCenter());
    }

    public double squaredCenterDistanceTo(BoundingBox other) {
        return center.squaredDistanceTo(other.getCenter());
    }


    public String forSvg() {
        return String.format("%5.1f %5.1f %5.1f %5.1f", minX, minY, maxX, maxY);
    }

    @Override
    public String toString() {
        return String.format("{minX=%5.4f, maxX=%5.4f, minY=%5.4f, maxY=%5.4f, width=%5.4f, height=%5.4f'}'", minX, maxX, minY, maxY, getWidth(), getHeight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoundingBox that = (BoundingBox) o;

        if (Double.compare(that.maxX, maxX) != 0) return false;
        if (Double.compare(that.maxY, maxY) != 0) return false;
        if (Double.compare(that.minX, minX) != 0) return false;
        if (Double.compare(that.minY, minY) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = minX != +0.0d ? Double.doubleToLongBits(minX) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = maxX != +0.0d ? Double.doubleToLongBits(maxX) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = minY != +0.0d ? Double.doubleToLongBits(minY) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = maxY != +0.0d ? Double.doubleToLongBits(maxY) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
