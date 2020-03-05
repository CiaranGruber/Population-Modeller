package graphing.model.settings;

import java.awt.*;

public abstract class GraphicalSettings {
    private Color colour;
    private boolean show;

    public GraphicalSettings() {
        this(Color.BLACK, true);
    }

    public GraphicalSettings(boolean show) {
        this(Color.BLACK, show);
    }

    public GraphicalSettings(Color colour) {
        this(colour, true);
    }

    public GraphicalSettings(Color colour, boolean show) {
        this.colour = colour;
        this.show = show;
    }

    public GraphicalSettings(GraphicalSettings otherSettings) {
        colour = otherSettings.colour;
        show = otherSettings.show;
    }

    protected Color getColour() {
        return colour;
    }

    protected void setColour(Color colour) {
        this.colour = colour;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
