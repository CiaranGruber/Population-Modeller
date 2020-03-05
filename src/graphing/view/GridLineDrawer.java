package graphing.view;

import graphing.model.settings.GridLineSettings;
import mathematics.operable.Fraction;
import mathematics.operable.OperableNumber;
import position.BasicPosition;
import position.ScaledDimension;

import java.awt.*;

public class GridLineDrawer {
    public static void draw(Graphics2D graphics, GridLineSettings gridLineSettings, Direction direction, ScaledDimension dimensions) {
        if (!gridLineSettings.isShow()) {
            return;
        }

        double pos;
        OperableNumber majorInterval, minorInterval;
        switch (direction) {
            case UP:
            case DOWN:
                pos = dimensions.getMinY();
                majorInterval = new Fraction(gridLineSettings.getMajorGap());
                break;
            default:
                pos = dimensions.getMinX();
                majorInterval = new Fraction(gridLineSettings.getMajorGap());
        }
        minorInterval = new Fraction(majorInterval).divide(new Fraction(gridLineSettings.getMinorCount() + 1));
        switch (direction) {
            case UP:
            case DOWN:
                while (pos < dimensions.getMaxY()) {
                    // Draw major line
                    drawMajorLines(graphics, new BasicPosition(dimensions.getMinX(), pos),
                            new BasicPosition(dimensions.getMaxX(), pos), gridLineSettings);
                    // Draw minor lines
                    if (gridLineSettings.getMinorCount() != 0 && gridLineSettings.getMinorLines().isShow()) {
                        graphics.setColor(gridLineSettings.getMinorLines().getColour());
                        graphics.setStroke(gridLineSettings.getMinorLines().getStroke());
                        for (int j = 0; j < gridLineSettings.getMinorCount() && pos + minorInterval.doubleValue() < dimensions.getMaxY(); ++j) {
                            pos += minorInterval.doubleValue();
                            graphics.drawLine((int) dimensions.getMinX(), (int) pos, (int) dimensions.getMaxX(), (int) pos);
                        }
                        pos += minorInterval.doubleValue();
                    } else {
                        pos += majorInterval.doubleValue();
                    }
                }
                break;
            default:
                while (pos < dimensions.getMaxX()) {
                    // Draw major line (or minor)
                    drawMajorLines(graphics, new BasicPosition(pos, dimensions.getMinY()),
                            new BasicPosition(pos, dimensions.getMaxY()), gridLineSettings);
                    // Draw minor lines
                    if (gridLineSettings.getMinorCount() != 0 && gridLineSettings.getMinorLines().isShow()) {
                        for (int j = 0; j < gridLineSettings.getMinorCount() && pos + minorInterval.doubleValue() < dimensions.getMaxX(); ++j) {
                            pos += minorInterval.doubleValue();
                            MiscDrawer.drawLine(graphics, new BasicPosition(pos, dimensions.getMinY()),
                                    new BasicPosition(pos, dimensions.getMaxY()), gridLineSettings.getMinorLines());
                        }
                        pos += minorInterval.doubleValue();
                    } else {
                        pos += majorInterval.doubleValue();
                    }
                }
        }
    }

    private static void drawMajorLines(Graphics2D graphics, BasicPosition pos1, BasicPosition pos2, GridLineSettings gridLineSettings) {
        if (!gridLineSettings.getMajorLines().isShow()) {
            MiscDrawer.drawLine(graphics, pos1, pos2, gridLineSettings.getMinorLines());
        } else {
            MiscDrawer.drawLine(graphics, pos1, pos2, gridLineSettings.getMajorLines());
        }
    }
}
