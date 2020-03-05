package graphing.model;

import graphing.model.settings.GraphSettings;

public abstract class AbstractGraph<PlotType extends GraphSection, SettingsType extends GraphSettings> {
    private String title;
    private PlotType graphPlot;
    private SettingsType graphSettings;

    public AbstractGraph(String title, PlotType graphPlot, SettingsType graphSettings) {
        this.title = title;
        this.graphPlot = graphPlot;
        this.graphSettings = graphSettings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PlotType getGraphPlot() {
        return graphPlot;
    }

    public void setGraphPlot(PlotType graphPlot) {
        this.graphPlot = graphPlot;
    }

    public SettingsType getGraphSettings() {
        return graphSettings;
    }

    public void setGraphSettings(SettingsType graphSettings) {
        this.graphSettings = graphSettings;
    }
}
