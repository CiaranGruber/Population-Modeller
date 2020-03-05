package position;

import position.exceptions.PointNotOnLineException;

public class MathematicalLine extends Line {
    private double gradient;
    private double intercept;

    public MathematicalLine(ScaledPosition firstPosition, ScaledPosition secondPosition) {
        if (firstPosition.x == secondPosition.x) {
            gradient = firstPosition.y > secondPosition.y ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            intercept = firstPosition.x;
        } else {
            gradient = (firstPosition.y - secondPosition.y) / (firstPosition.x - secondPosition.x);
            intercept = firstPosition.y - gradient * firstPosition.x;
        }
    }

    public MathematicalLine(double gradient, ScaledPosition position) {
        this.gradient = gradient;
        intercept = gradient * position.x - position.y;
    }

    public MathematicalLine(double gradient, double intercept, boolean interceptIsYBased) {
        this.gradient = gradient;
        if (Double.isInfinite(gradient)) {
            if (interceptIsYBased) {
                throw new PointNotOnLineException("Intercept must be y-based");
            }
            this.intercept = intercept;
        } else {
            if (interceptIsYBased) {
                this.intercept = intercept;
            } else {
                this.intercept = -gradient * intercept;
            }
        }
    }

    public static MathematicalLine getDimensionEquation(ScaledDimension dimension, Side sideOfDimension) {
        switch (sideOfDimension) {
            case LEFT:
                return new MathematicalLine(dimension.getBottomLeftPosition(), dimension.getTopLeftPosition());
            case TOP:
                return new MathematicalLine(dimension.getTopLeftPosition(), dimension.getTopRightPosition());
            case RIGHT:
                return new MathematicalLine(dimension.getTopRightPosition(), dimension.getBottomRightPosition());
            default:
                return new MathematicalLine(dimension.getBottomLeftPosition(), dimension.getBottomRightPosition());
        }
    }

    @Override
    public double getGradient() {
        return gradient;
    }

    @Override
    public double getIntercept() {
        return intercept;
    }

    @Override
    public ScaledPosition getStartPos() {
        if (Double.isInfinite(gradient)) {
            if (gradient == Double.POSITIVE_INFINITY) {
                return new ScaledPosition(intercept, Double.NEGATIVE_INFINITY);
            } else {
                return new ScaledPosition(intercept, Double.POSITIVE_INFINITY);
            }
        } else if (gradient == 0) {
            return new ScaledPosition(Double.NEGATIVE_INFINITY, intercept);
        } else if (gradient > 0) {
            return new ScaledPosition(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        }
        return new ScaledPosition(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    @Override
    public ScaledPosition getEndPos() {
        if (Double.isInfinite(gradient)) {
            if (gradient == Double.POSITIVE_INFINITY) {
                return new ScaledPosition(intercept, Double.POSITIVE_INFINITY);
            } else {
                return new ScaledPosition(intercept, Double.NEGATIVE_INFINITY);
            }
        } else if (gradient == 0) {
            return new ScaledPosition(Double.POSITIVE_INFINITY, intercept);
        } else if (gradient > 0) {
            return new ScaledPosition(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        }
        return new ScaledPosition(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }
}
