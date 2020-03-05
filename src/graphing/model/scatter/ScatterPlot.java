package graphing.model.scatter;

import graphing.model.Axis;
import graphing.model.GraphSection;
import graphing.model.settings.ScatterPlotSettings;
import mathematics.operable.LosslessNumber;
import mathematics.operable.Range;

import java.util.ArrayList;

public class ScatterPlot extends GraphSection<ScatterData> {
    private Axis xAxis;
    private Axis yAxis;
    private ScatterPlotSettings plotSettings;

    public ScatterPlot(String xAxisTitle, String yAxisTitle) {
        this(new ArrayList<>(), xAxisTitle, yAxisTitle);
    }

    public ScatterPlot(ArrayList<ScatterData> data, String xAxisTitle, String yAxisTitle) {
        super(data);
        xAxis = new Axis(xAxisTitle, new Range<>(new LosslessNumber(Math.min(0, minXValue(data))),
                new LosslessNumber(Math.max(0, maxXValue(data)))));
        yAxis = new Axis(yAxisTitle, new Range<>(new LosslessNumber(Math.min(0, minYValue(data))),
                new LosslessNumber(Math.max(0, maxYValue(data)))));
        this.plotSettings = new ScatterPlotSettings();
    }

    public ScatterPlot(Axis xAxis, Axis yAxis) {
        this(new ArrayList<>(), xAxis, yAxis, new ScatterPlotSettings());
    }

    public ScatterPlot(ArrayList<ScatterData> data, Axis xAxis, Axis yAxis, ScatterPlotSettings plotSettings) {
        super(data);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.plotSettings = plotSettings;
    }

    private static double minXValue(ArrayList<ScatterData> data) {
        double minValue = Double.MAX_VALUE;
        for (ScatterData dataSet : data) {
            for (ScatterDataPoint dataPoint : dataSet.getData()) {
                if (dataPoint.getXValue() < minValue) {
                    minValue = dataPoint.getXValue();
                }
            }
        }
        return minValue;
    }

    private static double minYValue(ArrayList<ScatterData> data) {
        double minValue = Double.MAX_VALUE;
        for (ScatterData dataSet : data) {
            for (ScatterDataPoint dataPoint : dataSet.getData()) {
                if (dataPoint.getYValue() < minValue) {
                    minValue = dataPoint.getYValue();
                }
            }
        }
        return minValue;
    }

    private static double maxXValue(ArrayList<ScatterData> data) {
        double maxValue = Double.MIN_VALUE;
        for (ScatterData dataSet : data) {
            for (ScatterDataPoint dataPoint : dataSet.getData()) {
                if (dataPoint.getXValue() > maxValue) {
                    maxValue = dataPoint.getXValue();
                }
            }
        }
        return maxValue;
    }

    private static double maxYValue(ArrayList<ScatterData> data) {
        double maxValue = Double.MIN_VALUE;
        for (ScatterData dataSet : data) {
            for (ScatterDataPoint dataPoint : dataSet.getData()) {
                if (dataPoint.getYValue() > maxValue) {
                    maxValue = dataPoint.getYValue();
                }
            }
        }
        return maxValue;
    }

    public void resetAxes() {
        xAxis = new Axis(xAxis.getAxisTitle(), new Range<>(new LosslessNumber(Math.min(0, minXValue(getDataSets()))),
                new LosslessNumber(Math.max(0, maxXValue(getDataSets())))));
        yAxis = new Axis(yAxis.getAxisTitle(), new Range<>(new LosslessNumber(Math.min(0, minYValue(getDataSets()))),
                new LosslessNumber(Math.max(0, maxYValue(getDataSets())))));
    }

    public ScatterPlotSettings getPlotSettings() {
        return plotSettings;
    }

    public void setPlotSettings(ScatterPlotSettings plotSettings) {
        this.plotSettings = plotSettings;
    }

    public Axis getXAxis() {
        return xAxis;
    }

    public void setXAxis(Axis xAxis) {
        this.xAxis = xAxis;
    }

    public Axis getYAxis() {
        return yAxis;
    }

    public void setYAxis(Axis yAxis) {
        this.yAxis = yAxis;
    }
}
