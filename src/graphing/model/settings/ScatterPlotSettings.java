package graphing.model.settings;

import java.awt.*;

public class ScatterPlotSettings extends GraphicalSettings {
    private GridLineSettings horizontalGridLines;
    private GridLineSettings verticalGridLines;

    public ScatterPlotSettings() {
        this(true);
    }

    public ScatterPlotSettings(boolean show) {
        this(new Color(0, 0, 0, 0), new GridLineSettings(false), new GridLineSettings(false), show);
    }

    public ScatterPlotSettings(GridLineSettings gridLines) {
        this(new Color(0, 0, 0, 0), gridLines, gridLines, true);
    }

    public ScatterPlotSettings(GridLineSettings horizontalGridLines, GridLineSettings verticalGridLines) {
        this(new Color(0, 0, 0, 0), horizontalGridLines, verticalGridLines, true);
    }

    public ScatterPlotSettings(Color backgroundColour, GridLineSettings horizontalGridLines, GridLineSettings verticalGridLines, boolean show) {
        super(backgroundColour, show);
        this.horizontalGridLines = horizontalGridLines;
        this.verticalGridLines = verticalGridLines;
    }

    public Color getBackgroundColour() {
        return getColour();
    }

    public void setBackgroundColour(Color colour) {
        setColour(colour);
    }

    public GridLineSettings getHorizontalGridLines() {
        return horizontalGridLines;
    }

    public void setHorizontalGridLines(GridLineSettings horizontalGridLines) {
        this.horizontalGridLines = horizontalGridLines;
    }

    public GridLineSettings getVerticalGridLines() {
        return verticalGridLines;
    }

    public void setVerticalGridLines(GridLineSettings verticalGridLines) {
        this.verticalGridLines = verticalGridLines;
    }
}
