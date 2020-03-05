package graphing.model.scatter;

import graphing.model.GraphData;
import graphing.model.settings.LineSettings;
import graphing.model.settings.MarkerSettings;

import java.util.ArrayList;
import java.util.Comparator;

public class ScatterData extends GraphData<ScatterDataPoint> {
    private LineSettings lineSettings;
    private MarkerSettings defaultMarkerSettings;

    public ScatterData(String dataName, int idNum) {
        this(dataName, idNum, new LineSettings(false));
    }

    public ScatterData(String dataName, int idNum, LineSettings lineSettings) {
        super(dataName, idNum);
        this.lineSettings = lineSettings;
        this.defaultMarkerSettings = new MarkerSettings(true);
    }

    public ScatterData(String dataName, int idNum, MarkerSettings defaultMarkerSettings) {
        super(dataName, idNum);
        this.lineSettings = new LineSettings(false);
        this.defaultMarkerSettings = defaultMarkerSettings;
    }

    public ScatterData(String dataName, int idNum, LineSettings lineSettings, MarkerSettings defaultMarkerSettings) {
        super(dataName, idNum);
        this.lineSettings = lineSettings;
        this.defaultMarkerSettings = defaultMarkerSettings;
    }

    public ScatterData(String dataName, ArrayList<ScatterDataPoint> data, int idNum, LineSettings lineSettings, MarkerSettings defaultMarkerSettings) {
        super(dataName, data, idNum);
        this.lineSettings = lineSettings;
        this.defaultMarkerSettings = defaultMarkerSettings;
    }

    @Override
    public void addDataPoint(ScatterDataPoint dataPoint) {
        super.addDataPoint(dataPoint);
        if (!dataPoint.getMarkerSettings().isUnique()) {
            dataPoint.setMarkerSettings(defaultMarkerSettings);
        }
        reorganise();
    }

    public LineSettings getLineSettings() {
        return lineSettings;
    }

    public void setLineSettings(LineSettings lineSettings) {
        this.lineSettings = lineSettings;
    }

    public MarkerSettings getDefaultMarkerSettings() {
        return defaultMarkerSettings;
    }

    public void setDefaultMarkerSettings(MarkerSettings defaultMarkerSettings) {
        this.defaultMarkerSettings = defaultMarkerSettings;
    }

    public void reorganise() {
        data.sort(Comparator.comparing(ScatterDataPoint::getXValue));
    }
}
