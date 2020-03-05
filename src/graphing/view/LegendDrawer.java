package graphing.view;

import graphing.model.Legend;
import graphing.model.settings.LegendSettings;
import graphing.model.settings.LineSettings;
import graphing.model.settings.MarkerSettings;
import graphing.model.settings.TextSettings;
import mathematics.operable.Fraction;
import mathematics.operable.OperableNumber;
import org.javatuples.Pair;
import position.BasicPosition;
import position.ScaledDimension;

import java.awt.*;

public class LegendDrawer {
    public static void draw(Graphics2D graphics, Legend legend, ScaledDimension dimensions) {
        draw(graphics, legend, new LegendSettings(), dimensions);
    }

    public static void draw(Graphics2D graphics, Legend legend, LegendSettings legendSettings, ScaledDimension dimensions) {
        // Draw background
        drawBackground(graphics, dimensions, legendSettings.getBackgroundColour());

        // Draw border
        if (legendSettings.getBorder().isShow()) {
            drawBorder(graphics, dimensions, legendSettings.getBorder());
        }

        // Draw internal legend
        drawLegend(graphics, legend, legendSettings, dimensions);
    }

    private static void drawBackground(Graphics2D graphics, ScaledDimension dimensions, Color backgroundColour) {
        // Get initial settings
        Color prevColour = graphics.getColor();

        graphics.setColor(backgroundColour);
        graphics.fillRect((int) dimensions.getMinX(), (int) dimensions.getMinY(),
                dimensions.getIntWidth(), dimensions.getIntHeight());

        // Reset settings
        graphics.setColor(prevColour);
    }

    private static void drawBorder(Graphics2D graphics, ScaledDimension dimensions, LineSettings borderSettings) {
        // Get initial settings
        Stroke prevStroke = graphics.getStroke();
        Color prevColour = graphics.getColor();

        graphics.setColor(borderSettings.getColour());
        graphics.setStroke(borderSettings.getStroke());
        graphics.drawRect((int) dimensions.getMinX(), (int) dimensions.getMinY(),
                dimensions.getIntWidth(), dimensions.getIntHeight());

        // Reset settings
        graphics.setStroke(prevStroke);
        graphics.setColor(prevColour);
    }

    private static void drawLegend(Graphics2D graphics, Legend legend, LegendSettings legendSettings, ScaledDimension dimension) {
        TextSettings textSettings = legendSettings.getTextSettings();
        int textSize = textSettings.getFont().getSize();
        int legendCount = legend.getMainColourToData().size();

        MarkerSettings markerSettings = legendSettings.getMarkerSettings();
        markerSettings.setFilled(true);
        markerSettings.setSize(textSize);
        markerSettings.setShow(true);

        OperableNumber interval;
        if (legendCount > 1) {
            interval = new Fraction(dimension.getIntHeight() - 2 * textSize - textSize * legendCount,
                    legendCount - 1);
        } else {
            interval = new Fraction(0);
        }
        BasicPosition position = dimension.getTopLeftPosition().sumX(textSize).sumY(textSize);

        for (Pair<Color, String> legendItem : legend.getMainColourToData()) {
            // Draw marker
            markerSettings.setFillColour(legendItem.getValue0());
            MiscDrawer.drawMarker(graphics, new BasicPosition(position.x + textSize / 2.0, position.y + textSize / 2.0), markerSettings);
            // Draw text
            MiscDrawer.drawString(graphics, legendItem.getValue1(), new BasicPosition(position.getIntX() + 3.0 * textSize / 2,
                    position.y + textSize), textSettings);
            position.sumY(interval.doubleValue() + textSize);
        }
    }
}
