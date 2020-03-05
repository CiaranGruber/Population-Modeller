package graphing.model.settings;

import java.awt.*;

public class LineSettings extends GraphicalSettings {
    Stroke stroke;

    public LineSettings() {
        this(new BasicStroke());
    }

    public LineSettings(Stroke stroke) {
        this(Color.BLACK, stroke);
    }

    public LineSettings(Color colour) {
        this(colour, true);
    }

    public LineSettings(boolean show) {
        this(Color.BLACK, show);
    }

    public LineSettings(Color colour, Stroke stroke) {
        this(colour, stroke, true);
    }

    public LineSettings(Color colour, boolean show) {
        this(colour, new BasicStroke(), show);
    }

    public LineSettings(Color colour, Stroke stroke, boolean show) {
        super(colour, show);
        this.stroke = stroke;
    }

    public LineSettings(LineSettings otherSettings) {
        super(otherSettings);
        this.stroke = otherSettings.getStroke();
    }

    public Color getColour() {
        return super.getColour();
    }

    public void setColour(Color colour) {
        super.setColour(colour);
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
}
