package graphing.model.settings;

import java.awt.*;

public class MarkerSettings extends GraphicalSettings {
    private int size;
    private boolean filled;
    private LineSettings border;
    private ShapeType shapeType;
    private boolean unique;

    public MarkerSettings() {
        this(true);
        unique = false;
    }

    public MarkerSettings(boolean show) {
        this(Color.BLACK, new LineSettings(), 5, true, ShapeType.SQUARE, false);
    }

    public MarkerSettings(Color fillColour) {
        this(fillColour, new LineSettings(false));
    }

    public MarkerSettings(Color fillColour, LineSettings border) {
        this(fillColour, border, 5, true);
    }

    public MarkerSettings(Color fillColour, int size, ShapeType shape) {
        this(fillColour, new LineSettings(), size, true, shape, true);
    }

    public MarkerSettings(Color fillColour, LineSettings border, int size, boolean filled) {
        this(fillColour, border, size, filled, ShapeType.CIRCLE, true);
    }

    public MarkerSettings(Color fillColour, LineSettings border, int size, boolean filled, ShapeType shape, boolean show) {
        super(fillColour, show);
        this.filled = filled;
        this.border = border;
        this.shapeType = shape;
        this.size = size;
        unique = true;
    }

    public Color getFillColour() {
        return getColour();
    }

    public void setFillColour(Color fillColour) {
        setColour(fillColour);
        unique = true;
    }

    public LineSettings getBorder() {
        return border;
    }

    public void setBorder(LineSettings border) {
        this.border = border;
        unique = true;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
        unique = true;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        unique = true;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
        unique = true;
    }

    public boolean isUnique() {
        return unique;
    }
}
