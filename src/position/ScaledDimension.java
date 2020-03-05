package position;

import java.awt.*;
import java.util.Objects;

public class ScaledDimension {
    public ScaledPosition topLeftPosition;
    public ScaledPosition bottomRightPosition;

    public ScaledDimension(double minX, double minY, double maxX, double maxY) {
        this(minX, minY, maxX, maxY, 1);
    }

    public ScaledDimension(double minX, double minY, double maxX, double maxY, double scale) {
        this(new ScaledPosition(minX, minY, scale), new ScaledPosition(maxX, maxY, scale));
    }

    public ScaledDimension(Dimension dimension) {
        this(dimension.width, dimension.height, 1);
    }

    public ScaledDimension(Dimension dimension, double scale) {
        this(dimension.width, dimension.height, scale);
    }

    public ScaledDimension(double width, double height) {
        this(width, height, 1);
    }

    public ScaledDimension(double width, double height, double scale) {
        topLeftPosition = new ScaledPosition();
        bottomRightPosition = new ScaledPosition(width, height, scale);
    }

    public ScaledDimension(ScaledPosition topLeftPosition, double width, double height) {
        this(topLeftPosition, width, height, 1);
    }

    public ScaledDimension(ScaledPosition topLeftPosition, double width, double height, double scale) {
        this.topLeftPosition = topLeftPosition;
        bottomRightPosition = new ScaledPosition(topLeftPosition).sumX(width, scale).sumY(height, scale);
    }

    public ScaledDimension(ScaledPosition point1, ScaledPosition point2) {
        topLeftPosition = new ScaledPosition(Math.min(point1.x, point2.x), Math.min(point1.y, point2.y));
        bottomRightPosition = new ScaledPosition(Math.max(point1.x, point2.x), Math.max(point1.y, point2.y));
    }

    public ScaledDimension(ScaledDimension dimension) {
        topLeftPosition = new ScaledPosition(dimension.topLeftPosition);
        bottomRightPosition = new ScaledPosition(dimension.bottomRightPosition);
    }

    public ScaledDimension getScaledDimension(double scale) {
        return new ScaledDimension(topLeftPosition.getScaledPosition(scale), getWidth(scale), getHeight(scale));
    }

    public ScaledDimension getScaledDimension(double xScale, double yScale) {
        return new ScaledDimension(topLeftPosition.getScaledPosition(xScale, yScale), getWidth(xScale), getHeight(yScale));
    }

    public ScaledDimension sumX(double x, double scale) {
        topLeftPosition.sumX(x, scale);
        bottomRightPosition.sumX(x, scale);
        return this;
    }

    public ScaledDimension sumY(double y, double scale) {
        topLeftPosition.sumY(y, scale);
        bottomRightPosition.sumY(y, scale);
        return this;
    }

    public ScaledDimension sum(ScaledPosition position) {
        topLeftPosition.sum(position);
        bottomRightPosition.sum(position);
        return this;
    }

    public Dimension getDimension() {
        return getDimension(1);
    }

    public Dimension getDimension(double scale) {
        return new Dimension((int) getWidth(scale), (int) getHeight(scale));
    }

    public double getMinX() {
        return getMinX(1);
    }

    public double getMinX(double scale) {
        return topLeftPosition.getX(scale);
    }

    public double getMinY() {
        return getMinY(1);
    }

    public double getMinY(double scale) {
        return topLeftPosition.getY(scale);
    }

    public double getMaxX() {
        return getMaxX(1);
    }

    public double getMaxX(double scale) {
        return bottomRightPosition.getX(scale);
    }

    public double getMaxY() {
        return getMaxY(1);
    }

    public double getMaxY(double scale) {
        return bottomRightPosition.getY(scale);
    }

    public int getIntWidth() {
        return getIntWidth(1);
    }

    public int getIntWidth(double scale) {
        return (int) getWidth(scale);
    }

    public double getWidth() {
        return getWidth(1);
    }

    public double getWidth(double scale) {
        return bottomRightPosition.getX(scale) - topLeftPosition.getX(scale);
    }

    public int getIntHeight() {
        return getIntHeight(1);
    }

    public int getIntHeight(double scale) {
        return (int) getHeight(scale);
    }

    public double getHeight() {
        return getHeight(1);
    }

    public double getHeight(double scale) {
        return bottomRightPosition.getY(scale) - topLeftPosition.getY(scale);
    }

    public ScaledPosition getCentrePoint() {
        return new ScaledPosition(topLeftPosition.x + getWidth() / 2, topLeftPosition.y + getHeight() / 2);
    }

    public boolean containsPoint(ScaledPosition point) {
        return point.x >= topLeftPosition.x && point.x <= bottomRightPosition.x && point.y >= topLeftPosition.y && point.y <= bottomRightPosition.y;
    }

    public boolean containsPoints(ScaledPosition[] points) {
        for (ScaledPosition point : points) {
            if (!containsPoint(point)) {
                return false;
            }
        }
        return true;
    }

    public RelativeArea areaOfPoint(ScaledPosition point) {
        // If point is within bounds, return CENTRE
        if (containsPoint(point)) return RelativeArea.CENTRE;
        if (point.x <= getMinX() && point.y <= getMinY()) return RelativeArea.TOP_LEFT;
        if (point.x <= getMinX() && point.y >= getMaxY()) return RelativeArea.BOTTOM_LEFT;
        if (point.x >= getMaxX() && point.y <= getMinY()) return RelativeArea.TOP_RIGHT;
        if (point.x >= getMaxX() && point.y >= getMaxY()) return RelativeArea.BOTTOM_RIGHT;
        if (point.x < getMinX()) return RelativeArea.LEFT;
        if (point.y < getMinY()) return RelativeArea.TOP;
        if (point.x > getMaxX()) return RelativeArea.RIGHT;
        return RelativeArea.BOTTOM;
    }

    public ScaledPosition getPointInLocation(RelativeArea pointLocation) {
        switch (pointLocation) {
            case CENTRE:
                return new ScaledPosition((getMinX() + getMaxX()) / 2, (getMinY() + getMaxY()) / 2);
            case TOP_LEFT:
                return new ScaledPosition(topLeftPosition);
            case TOP:
                return new ScaledPosition((getMinX() + getMaxX()) / 2, getMinY());
            case TOP_RIGHT:
                return new ScaledPosition(getMaxX(), getMinY());
            case RIGHT:
                return new ScaledPosition(getMaxX(), (getMinY() + getMaxY()) / 2);
            case BOTTOM_RIGHT:
                return new ScaledPosition(bottomRightPosition);
            case BOTTOM:
                return new ScaledPosition((getMinX() + getMaxX()) / 2, getMaxY());
            case BOTTOM_LEFT:
                return new ScaledPosition(getMinX(), getMaxY());
            default:
                return new ScaledPosition(getMinX(), (getMinY() + getMaxY()) / 2);
        }
    }

    public void resetToOrigin() {
        bottomRightPosition.x = getWidth();
        bottomRightPosition.y = getHeight();
        topLeftPosition = new ScaledPosition();
    }

    public ScaledPosition getTopLeftPosition() {
        return new ScaledPosition(topLeftPosition);
    }

    public ScaledPosition getTopRightPosition() {
        return new ScaledPosition(bottomRightPosition.x, topLeftPosition.y);
    }

    public ScaledPosition getBottomLeftPosition() {
        return new ScaledPosition(topLeftPosition.x, bottomRightPosition.y);
    }

    public ScaledPosition getBottomRightPosition() {
        return new ScaledPosition(bottomRightPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaledDimension that = (ScaledDimension) o;
        return topLeftPosition.equals(that.topLeftPosition) &&
                bottomRightPosition.equals(that.bottomRightPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeftPosition, bottomRightPosition);
    }

    @Override
    public String toString() {
        return "(" + Math.round(getWidth() * 10000) / 10000.0 + ", " + Math.round(getHeight() * 10000) / 10000.0 + ")";
    }
}
