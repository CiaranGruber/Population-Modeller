package position;

import java.awt.*;
import java.util.Objects;

public class BasicPosition {
    public double x;
    public double y;

    public BasicPosition() {
        this(0, 0);
    }

    public BasicPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public BasicPosition(BasicPosition position) {
        x = position.x;
        y = position.y;
    }

    public Point getPoint() {
        return new Point(getIntX(), getIntY());
    }

    public int getIntX() {
        return (int) x;
    }

    public int getIntY() {
        return (int) y;
    }

    public BasicPosition sumX(double x) {
        this.x += x;
        return this;
    }

    public BasicPosition sumY(double y) {
        this.y += y;
        return this;
    }

    public BasicPosition sum(BasicPosition position) {
        sumX(position.x);
        sumY(position.y);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicPosition)) return false;
        BasicPosition that = (BasicPosition) o;
        return Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + Math.round(x * 10000) / 10000.0 + ", " + Math.round(y * 10000) / 10000.0 + ")";
    }
}
