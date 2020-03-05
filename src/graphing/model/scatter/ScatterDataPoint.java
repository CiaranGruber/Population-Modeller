package graphing.model.scatter;

import graphing.model.DataPoint;
import graphing.model.settings.MarkerSettings;

public class ScatterDataPoint extends DataPoint {
    private double yValue;
    private MarkerSettings markerSettings;

    public ScatterDataPoint(double xValue, double yValue) {
        super(xValue);
        this.yValue = yValue;
        this.markerSettings = new MarkerSettings();
    }

    public ScatterDataPoint(double xValue, double yValue, MarkerSettings markerSettings) {
        super(xValue);
        this.yValue = yValue;
        this.markerSettings = markerSettings;
    }

    public double getXValue() {
        return getValue();
    }

    public void setXValue(double xValue) {
        setValue(xValue);
    }

    public double getYValue() {
        return yValue;
    }

    public void setYValue(double yValue) {
        this.yValue = yValue;
    }

    public MarkerSettings getMarkerSettings() {
        return markerSettings;
    }

    public void setMarkerSettings(MarkerSettings markerSettings) {
        this.markerSettings = markerSettings;
    }
}
