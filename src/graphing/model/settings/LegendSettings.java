package graphing.model.settings;

import java.awt.*;

public class LegendSettings extends GraphicalSettings {
    private LineSettings border;
    private MarkerSettings markerSettings;
    private TextSettings textSettings;

    public LegendSettings() {
        this(true);
    }

    public LegendSettings(boolean show) {
        this(new Color(0, 0, 0, 0), new LineSettings(false), new MarkerSettings(), new TextSettings(), show);
    }

    public LegendSettings(MarkerSettings markerSettings, TextSettings textSettings) {
        this(new Color(0, 0, 0, 0), new LineSettings(false), markerSettings, textSettings);
    }

    public LegendSettings(Color backgroundColour, LineSettings border, MarkerSettings markerSettings, TextSettings textSettings) {
        this(backgroundColour, border, markerSettings, textSettings, true);
    }

    public LegendSettings(Color backgroundColour, LineSettings border, MarkerSettings markerSettings, TextSettings textSettings, boolean show) {
        super(backgroundColour, show);
        this.border = border;
        this.markerSettings = markerSettings;
        this.textSettings = textSettings;
    }

    public Color getBackgroundColour() {
        return getColour();
    }

    public void setBackgroundColour(Color backgroundColour) {
        setColour(backgroundColour);
    }

    public LineSettings getBorder() {
        return border;
    }

    public void setBorder(LineSettings border) {
        this.border = border;
    }

    public MarkerSettings getMarkerSettings() {
        return markerSettings;
    }

    public void setMarkerSettings(MarkerSettings markerSettings) {
        this.markerSettings = markerSettings;
    }

    public TextSettings getTextSettings() {
        return textSettings;
    }

    public void setTextSettings(TextSettings textSettings) {
        this.textSettings = textSettings;
    }
}
