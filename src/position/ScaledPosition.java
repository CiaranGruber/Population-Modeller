package position;

import java.awt.*;

public class ScaledPosition extends BasicPosition {
    public ScaledPosition() {
        super();
    }

    public ScaledPosition(double x, double y) {
        super(x, y);
    }

    public ScaledPosition(double x, double y, double scale) {
        super(x * scale, y * scale);
    }

    public ScaledPosition(ScaledPosition scaledPosition) {
        super(scaledPosition);
    }

    public ScaledPosition(BasicPosition position) {
        super(position);
    }

    public static int getIntPos(double pos, double scale) {
        return (int) getPos(pos, scale);
    }

    public static double getPos(double pos, double scale) {
        return pos * scale;
    }

    public ScaledPosition getScaledPosition(double scale) {
        return new ScaledPosition(getX(scale), getY(scale));
    }

    public ScaledPosition getScaledPosition(double xScale, double yScale) {
        return new ScaledPosition(getX(xScale), getY(yScale));
    }

    public Point getPoint(double scale) {
        return new Point(getIntX(scale), getIntY(scale));
    }

    public int getIntX(double scale) {
        return (int) getX(scale);
    }

    public double getX(double scale) {
        return x * scale;
    }

    public void setX(double x, double scale) {
        this.x = x / scale;
    }

    public int getIntY(double scale) {
        return (int) getY(scale);
    }

    public double getY(double scale) {
        return y * scale;
    }

    public void setY(double y, double scale) {
        this.y = y / scale;
    }

    public ScaledPosition sumX(double x) {
        return sumX(x, 1);
    }

    public ScaledPosition sumX(double x, double scale) {
        this.x += x / scale;
        return this;
    }

    public ScaledPosition sumY(double y) {
        return sumY(y, 1);
    }

    public ScaledPosition sumY(double y, double scale) {
        this.y += y / scale;
        return this;
    }

    public ScaledPosition sum(BasicPosition position, double scale) {
        sumX(position.x, scale);
        sumY(position.y, scale);
        return this;
    }

    public ScaledPosition scaleX(double scale) {
        x = getX(scale);
        return this;
    }

    public ScaledPosition scaleY(double scale) {
        y = getY(scale);
        return this;
    }

    public void setRelativeToDimension(ScaledDimension dimension) {
        x -= dimension.getMinX();
        y -= dimension.getMinY();
    }
}
