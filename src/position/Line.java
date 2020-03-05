package position;

import position.exceptions.InfiniteIntersectionsFoundException;
import position.exceptions.NoIntersectionsFoundException;
import position.exceptions.PointNotOnLineException;

public abstract class Line {
    public abstract double getGradient();

    public abstract double getIntercept();

    public abstract ScaledPosition getStartPos();

    public abstract ScaledPosition getEndPos();

    public ScaledPosition getIntersection(Line secondLine) {
        // Test parallel lines
        if (getGradient() == secondLine.getGradient()) {
            if (getIntercept() == secondLine.getIntercept()) {
                throw new InfiniteIntersectionsFoundException("Lines are the same");
            }
            throw new NoIntersectionsFoundException("Lines are parallel");
        }

        // Test if first line is vertical
        if (Double.isInfinite(getGradient())) {
            return new ScaledPosition(getStartPos().x, secondLine.getGradient() * getStartPos().x + secondLine.getIntercept());
        }
        // Test if second line is vertical
        if (Double.isInfinite(secondLine.getGradient())) {
            return new ScaledPosition(secondLine.getStartPos().x, getGradient() * secondLine.getStartPos().x + getIntercept());
        }
        // Calculate the intersection if gradient is not Infinite
        double xValue = (getIntercept() - secondLine.getIntercept()) / (secondLine.getGradient() - getGradient());
        return new ScaledPosition(xValue, getGradient() * xValue + getIntercept());
    }

    public boolean isPointOnLine(ScaledPosition position) {
        if (getGradient() == Double.POSITIVE_INFINITY) {
            return position.x == getIntercept() && position.y >= getStartPos().y && position.y <= getEndPos().y;
        } else if (getGradient() == Double.NEGATIVE_INFINITY) {
            return position.x == getIntercept() && position.y >= getEndPos().y && position.y <= getStartPos().y;
        }

        // Make sure x values are within ranges
        if (position.x < Math.min(getStartPos().x, getEndPos().x) || position.x > Math.max(getStartPos().x, getEndPos().x)) {
            return false;
        }
        return getGradient() * position.x + getIntercept() == position.y;
    }

    public boolean doLinesIntersect(Line secondLine) {
        try {
            return isPointOnLine(getIntersection(secondLine));
        } catch (NoIntersectionsFoundException e) {
            return false;
        } catch (InfiniteIntersectionsFoundException e) {
            return true;
        }
    }

    public double getDistanceFromStart(ScaledPosition position) {
        if (!isPointOnLine(position)) {
            throw new PointNotOnLineException(position + " not found on line");
        }
        if (Double.isInfinite(getStartPos().x) || Double.isInfinite(getStartPos().y)) {
            return Double.POSITIVE_INFINITY;
        }
        if (Double.isInfinite(getGradient())) {
            return Math.abs(position.y - getStartPos().y);
        }
        return Math.sqrt(Math.pow(position.x - getStartPos().x, 2) + Math.pow(position.y - getStartPos().y, 2));
    }

    public ScaledPosition getPointFromDistance(double distanceFromStart) {
        double degrees = Math.atan(getGradient());
        ScaledPosition changePosition = new ScaledPosition(distanceFromStart * Math.cos(degrees), distanceFromStart * Math.sin(degrees));
        ScaledPosition startPos = getStartPos();
        return null;
    }
}
