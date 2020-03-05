package graphing.model.settings;

import java.awt.*;

public class ScatterGraphSettings extends GraphSettings {
    private LegendSettings legendSettings;
    private ScatterPlotSettings scatterPlotSettings;

    public ScatterGraphSettings() {
        this(true);
    }

    public ScatterGraphSettings(boolean show) {
        super(show);
        this.legendSettings = new LegendSettings();
        this.scatterPlotSettings = new ScatterPlotSettings();
    }

    public ScatterGraphSettings(TextSettings titleSettings, LegendSettings legendSettings, ScatterPlotSettings scatterPlotSettings) {
        super(titleSettings);
        this.legendSettings = legendSettings;
        this.scatterPlotSettings = scatterPlotSettings;
    }

    public ScatterGraphSettings(Color backgroundColour, TextSettings titleSettings, LegendSettings legendSettings,
                                ScatterPlotSettings scatterPlotSettings, boolean show) {
        super(backgroundColour, titleSettings, show);
        this.legendSettings = legendSettings;
        this.scatterPlotSettings = scatterPlotSettings;
    }

    public LegendSettings getLegendSettings() {
        return legendSettings;
    }

    public void setLegendSettings(LegendSettings legendSettings) {
        this.legendSettings = legendSettings;
    }

    public ScatterPlotSettings getScatterPlotSettings() {
        return scatterPlotSettings;
    }

    public void setScatterPlotSettings(ScatterPlotSettings scatterPlotSettings) {
        this.scatterPlotSettings = scatterPlotSettings;
    }
}
