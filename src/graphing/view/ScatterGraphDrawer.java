package graphing.view;

import graphing.model.scatter.ScatterGraph;
import graphing.model.settings.ScatterGraphSettings;
import graphing.model.settings.TextSettings;
import position.BasicPosition;
import position.ScaledDimension;
import position.ScaledPosition;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ScatterGraphDrawer {
    public static void draw(Graphics2D graphics, ScatterGraph scatterGraph, ScaledDimension dimensions) {
        ScatterGraphSettings graphSettings = scatterGraph.getGraphSettings();

        if (!graphSettings.isShow()) {
            return;
        }

        MiscDrawer.fillRect(graphics, graphSettings.getBackgroundColour(), dimensions);

        ScaledDimension titleDim = new ScaledDimension(new ScaledPosition(0, 0), 1, 0.2);
        ScaledDimension mainDim = new ScaledDimension(new ScaledPosition(0, 0.2), 0.8, 0.8);
        ScaledDimension legendDim = new ScaledDimension(new ScaledPosition(0.8, 0.5), 0.2, 0.3);
        titleDim = titleDim.getScaledDimension(dimensions.getWidth(), dimensions.getHeight());
        titleDim.sum(dimensions.getTopLeftPosition());
        mainDim = mainDim.getScaledDimension(dimensions.getWidth(), dimensions.getHeight());
        mainDim.sum(dimensions.getTopLeftPosition());
        legendDim = legendDim.getScaledDimension(dimensions.getWidth(), dimensions.getHeight());
        legendDim.sum(dimensions.getTopLeftPosition());

        drawTitle(graphics, scatterGraph.getTitle(), scatterGraph.getGraphSettings().getTitleSettings(), titleDim);

        ScatterPlotDrawer.draw(graphics, scatterGraph.getGraphPlot(), mainDim);

        LegendDrawer.draw(graphics, scatterGraph.getLegend(), scatterGraph.getGraphSettings().getLegendSettings(), legendDim);
    }

    public static void drawTitle(Graphics2D graphics, String title, TextSettings titleSettings, ScaledDimension dimensions) {
        FontMetrics fontMetrics = graphics.getFontMetrics(titleSettings.getFont());

        Rectangle2D box = fontMetrics.getStringBounds(title, graphics);
        ScaledDimension titleDimensions = new ScaledDimension(box.getWidth(), box.getHeight());
        BasicPosition titlePosition = dimensions.getTopLeftPosition();
        titlePosition.sumX((dimensions.getWidth() - titleDimensions.getWidth()) / 2);
        titlePosition.sumY((dimensions.getHeight() - titleDimensions.getHeight()) / 2);

        MiscDrawer.drawString(graphics, title, titlePosition, titleSettings);
    }
}
