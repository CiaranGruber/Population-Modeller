package position;

import position.exceptions.InfiniteIntersectionsFoundException;

public class RestrictedLine extends Line {
    private ScaledPosition startPos;
    private ScaledPosition endPos;

    public RestrictedLine(ScaledPosition startPos, ScaledPosition endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public RestrictedLine(RestrictedLine restrictedLine) {
        this.startPos = new ScaledPosition(restrictedLine.startPos);
        this.endPos = new ScaledPosition(restrictedLine.endPos);
    }

    @Override
    public double getIntercept() {
        double gradient = getGradient();
        if (Double.isInfinite(gradient)) {
            return Double.NaN;
        }
        return startPos.y - gradient * startPos.x;
    }

    @Override
    public double getGradient() {
        if (startPos.x == endPos.x) {
            if (startPos.y < endPos.y) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        } else if (startPos.y == endPos.y) {
            return 0;
        } else {
            return (endPos.y - startPos.y) / (endPos.x - startPos.x);
        }
    }

    @Override
    public ScaledPosition getStartPos() {
        return startPos;
    }

    @Override
    public ScaledPosition getEndPos() {
        return endPos;
    }

    public RestrictedLine trimLine(ScaledDimension dimension) {
        RestrictedLine newLine = new RestrictedLine(this);
        MathematicalLine leftSide = MathematicalLine.getDimensionEquation(dimension, Side.LEFT);
        MathematicalLine topSide = MathematicalLine.getDimensionEquation(dimension, Side.TOP);
        MathematicalLine rightSide = MathematicalLine.getDimensionEquation(dimension, Side.RIGHT);
        MathematicalLine bottomSide = MathematicalLine.getDimensionEquation(dimension, Side.BOTTOM);
        trimVertical(dimension, newLine, leftSide); // Manage intersections with left side
        trimHorizontal(dimension, newLine, topSide); // Manage intersections with top side
        trimVertical(dimension, newLine, rightSide); // Manage intersections with right side
        trimHorizontal(dimension, newLine, bottomSide); // Manage intersections with bottom side
        return newLine;
    }

    private void trimHorizontal(ScaledDimension dimension, RestrictedLine newLine, MathematicalLine horizontalSide) {
        try {
            if (doLinesIntersect(horizontalSide)) {
                if (dimension.containsPoint(endPos)) {
                    newLine.startPos = getIntersection(horizontalSide);
                } else {
                    newLine.endPos = getIntersection(horizontalSide);
                }
            }
        } catch (InfiniteIntersectionsFoundException e) {
            if (startPos.x > dimension.getMaxX()) newLine.startPos.x = dimension.getMaxX();
            else if (startPos.x < dimension.getMinX()) newLine.startPos.x = dimension.getMinX();
            if (endPos.x > dimension.getMaxX()) newLine.endPos.x = dimension.getMaxX();
            else if (endPos.x < dimension.getMinX()) newLine.endPos.x = dimension.getMinX();
        }
    }

    private void trimVertical(ScaledDimension dimension, RestrictedLine newLine, MathematicalLine verticalSide) {
        try {
            if (doLinesIntersect(verticalSide)) {
                if (dimension.containsPoint(endPos)) {
                    newLine.startPos = getIntersection(verticalSide);
                } else {
                    newLine.endPos = getIntersection(verticalSide);
                }
            }
        } catch (InfiniteIntersectionsFoundException e) {
            if (startPos.y > dimension.getMaxY()) newLine.startPos.y = dimension.getMaxY();
            else if (startPos.y < dimension.getMinY()) newLine.startPos.y = dimension.getMinY();
            if (endPos.y > dimension.getMaxY()) newLine.endPos.y = dimension.getMaxY();
            else if (endPos.y < dimension.getMinY()) newLine.endPos.y = dimension.getMinY();
        }
    }
}
