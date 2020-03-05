package graphing.view;

import graphing.model.scatter.ScatterData;
import graphing.model.scatter.ScatterPlot;
import graphing.model.settings.ScatterPlotSettings;
import position.ScaledDimension;
import position.ScaledPosition;

import java.awt.*;

public class ScatterPlotDrawer {
    public static void draw(Graphics2D graphics, ScatterPlot scatterPlot, ScaledDimension dimensions) {
        ScatterPlotSettings plotSettings = scatterPlot.getPlotSettings();

        if (!plotSettings.isShow()) {
            return;
        }

        ScaledDimension verticalAxisDim = new ScaledDimension(new ScaledPosition(0, 0), 0.2, 0.8);
        ScaledDimension horizontalAxisDim = new ScaledDimension(new ScaledPosition(0.2, 0.8), 0.8, 0.2);
        ScaledDimension mainAreaDim = new ScaledDimension(new ScaledPosition(0.2, 0), 0.8, 0.8);
        verticalAxisDim = verticalAxisDim.getScaledDimension(dimensions.getWidth(), dimensions.getHeight());
        verticalAxisDim.sum(dimensions.getTopLeftPosition());
        horizontalAxisDim = horizontalAxisDim.getScaledDimension(dimensions.getWidth(), dimensions.getHeight());
        horizontalAxisDim.sum(dimensions.getTopLeftPosition());
        mainAreaDim = mainAreaDim.getScaledDimension(dimensions.getWidth(), dimensions.getHeight());
        mainAreaDim.sum(dimensions.getTopLeftPosition());

        MiscDrawer.fillRect(graphics, plotSettings.getBackgroundColour(), mainAreaDim);

        GridLineDrawer.draw(graphics, plotSettings.getHorizontalGridLines(), Direction.UP, mainAreaDim);
        GridLineDrawer.draw(graphics, plotSettings.getVerticalGridLines(), Direction.RIGHT, mainAreaDim);

        for (ScatterData scatterData : scatterPlot.getDataSets()) {
            ScatterDataDrawer.draw(graphics, scatterData, scatterPlot.getXAxis().getRange(), scatterPlot.getYAxis().getRange(), mainAreaDim);
        }

        AxisDrawer.draw(graphics, Direction.RIGHT, scatterPlot.getXAxis(), horizontalAxisDim);
        AxisDrawer.draw(graphics, Direction.UP, scatterPlot.getYAxis(), verticalAxisDim);
    }
}
