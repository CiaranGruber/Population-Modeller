package graphing.view;

import graphing.model.Axis;
import graphing.model.settings.AxisSettings;
import graphing.model.settings.LineSettings;
import graphing.model.settings.TextSettings;
import mathematics.operable.Fraction;
import mathematics.operable.OperableNumber;
import position.BasicPosition;
import position.ScaledDimension;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AxisDrawer {
    public static void draw(Graphics2D graphics, Direction direction, Axis axis, ScaledDimension dimensions) {
        AxisSettings axisSettings = axis.getAxisSettings();

        if (!axisSettings.isShow()) {
            return;
        }

        // Draw main line
        drawAxisLine(graphics, axisSettings.getAxisLineSettings(), direction, dimensions);

        // Draw points on axis
        if (axisSettings.getPointsSettings().isShow() || axisSettings.getPointsTextSettings().isShow()) {
            drawAxisPoints(graphics, axis, direction, dimensions);
        }

        // Draw title
        if (axisSettings.getAxisTitleSettings().isShow()) {
            drawAxisTitle(graphics, axis.getAxisTitle(), axisSettings.getAxisTitleSettings(), direction, dimensions);
        }
    }

    private static void drawAxisLine(Graphics2D graphics, LineSettings axisLineSettings, Direction direction, ScaledDimension dimensions) {
        // Draw line
        BasicPosition pos1, pos2;
        switch (direction) {
            case UP:
                pos1 = dimensions.getTopRightPosition();
                pos2 = dimensions.getBottomRightPosition();
                break;
            case DOWN:
                pos1 = dimensions.getTopLeftPosition();
                pos2 = dimensions.getBottomLeftPosition();
                break;
            case RIGHT:
                pos1 = dimensions.getTopLeftPosition();
                pos2 = dimensions.getTopRightPosition();
                break;
            default:
                pos1 = dimensions.getBottomLeftPosition();
                pos2 = dimensions.getBottomRightPosition();
        }
        MiscDrawer.drawLine(graphics, pos1, pos2, axisLineSettings);
    }

    private static void drawAxisPoints(Graphics2D graphics, Axis axis, Direction direction, ScaledDimension dimensions) {
        AxisSettings axisSettings = axis.getAxisSettings();
        final int TITLE_GAP = 10;
        final int POINTS_GAP = 5;

        // Get interval between points
        boolean showLine = axisSettings.getPointsSettings().isShow();
        boolean showText = axisSettings.getPointsTextSettings().isShow();
        int lineLength;
        OperableNumber interval = new Fraction(axisSettings.getAxisGap());
        OperableNumber valueInterval;
        OperableNumber value = axis.getRange().getMinValue();
        BasicPosition position;

        FontMetrics fontMetrics = graphics.getFontMetrics(axisSettings.getPointsTextSettings().getFont());
        switch (direction) {
            case UP:
                position = dimensions.getBottomRightPosition();
                valueInterval = new Fraction(interval).divide(new Fraction(dimensions.getHeight()));
                break;
            case DOWN:
                valueInterval = new Fraction(interval).divide(new Fraction(dimensions.getHeight()));
                position = dimensions.getBottomLeftPosition();
                break;
            case LEFT:
                valueInterval = new Fraction(interval).divide(new Fraction(dimensions.getWidth()));
                position = dimensions.getBottomLeftPosition();
                break;
            default:
                valueInterval = new Fraction(interval).divide(new Fraction(dimensions.getWidth()));
                position = dimensions.getTopLeftPosition();
        }
        valueInterval.multiply(axis.getRange().getRange());

        if (showLine) {
            FontMetrics titleMetrics = graphics.getFontMetrics(axisSettings.getAxisTitleSettings().getFont());
            int pointsWidth, titleHeight;
            if (showText) {
                pointsWidth = fontMetrics.stringWidth(Double.toString(axis.getRange().getMaxValue().doubleValue()));
            } else {
                pointsWidth = POINTS_GAP; // Removes gap
            }
            if (axisSettings.getAxisTitleSettings().isShow()) {
                titleHeight = (int) titleMetrics.getStringBounds(axis.getAxisTitle(), graphics).getHeight();
            } else {
                titleHeight = TITLE_GAP;
            }
            switch (direction) {
                case UP:
                case DOWN:
                    lineLength = dimensions.getIntWidth();
                    break;
                default:
                    lineLength = dimensions.getIntHeight();
            }
            lineLength = Math.max(lineLength - pointsWidth - POINTS_GAP - titleHeight - TITLE_GAP, 5);
        } else {
            lineLength = 0;
        }

        // Draw lines and text (while value < maxRange)
        while (axis.getRange().getMaxValue().compareTo(value) >= 0) {
            int lengthOfText = fontMetrics.stringWidth(Integer.toString(value.intValue()));
            switch (direction) {
                case UP:
                    if (showLine) {
                        MiscDrawer.drawLine(graphics, position, new BasicPosition(position.x - lineLength, position.y), axisSettings.getPointsSettings());
                    }
                    if (showText) {
                        MiscDrawer.drawString(graphics, value.toBigDecimal(0).toString(),
                                new BasicPosition(position).sumX(-lineLength - lengthOfText - 5).sumY(fontMetrics.getHeight() / 4.0),
                                axisSettings.getPointsTextSettings(), 0);
                    }
                    position.sumY(-interval.doubleValue());
                    break;
                case DOWN:
                    if (showLine) {
                        MiscDrawer.drawLine(graphics, position, new BasicPosition(position).sumX(lineLength), axisSettings.getPointsSettings());
                    }
                    if (showText) {
                        MiscDrawer.drawString(graphics, Integer.toString(value.intValue()),
                                new BasicPosition(position).sumX(lineLength + 5).sumY(fontMetrics.getHeight() / 4.0),
                                axisSettings.getPointsTextSettings(), 0);
                    }
                    position.sumY(-interval.doubleValue());
                    break;
                case RIGHT:
                    if (showLine) {
                        MiscDrawer.drawLine(graphics, position, new BasicPosition(position).sumY(lineLength), axisSettings.getPointsSettings());
                    }
                    if (showText) {
                        MiscDrawer.drawString(graphics, Integer.toString(value.intValue()),
                                new BasicPosition(position).sumX(-fontMetrics.getHeight() / 4.0).sumY(lineLength + 5),
                                axisSettings.getPointsTextSettings(), 90);
                    }
                    position.sumX(interval.doubleValue());
                    break;
                default:
                    if (showLine) {
                        MiscDrawer.drawLine(graphics, position, new BasicPosition(position).sumY(-lineLength), axisSettings.getPointsSettings());
                    }
                    if (showText) {
                        MiscDrawer.drawString(graphics, Integer.toString(value.intValue()),
                                new BasicPosition(position).sumX(-fontMetrics.getHeight() / 4.0).sumY(-lengthOfText - lineLength - 5),
                                axisSettings.getPointsTextSettings(), 90);
                    }
                    position.sumX(interval.doubleValue());
            }
            value = value.add(valueInterval);
        }
    }

    public static void drawAxisTitle(Graphics2D graphics, String title, TextSettings titleSettings, Direction direction, ScaledDimension dimensions) {
        BasicPosition titleLocation;
        ScaledDimension titleDimensions;
        FontMetrics fontMetrics = graphics.getFontMetrics(titleSettings.getFont());

        // Get dimensions of string
        Rectangle2D box = fontMetrics.getStringBounds(title, graphics);
        titleDimensions = new ScaledDimension(box.getWidth(), box.getHeight());

        switch (direction) {
            case UP:
                titleLocation = dimensions.getBottomLeftPosition();
                titleLocation.sumX(titleDimensions.getHeight());
                titleLocation.sumY(-(dimensions.getHeight() - titleDimensions.getWidth()) / 2);
                break;
            case DOWN:
                titleLocation = dimensions.getTopRightPosition();
                titleLocation.sumX(-titleDimensions.getHeight());
                titleLocation.sumY((dimensions.getHeight() - titleDimensions.getWidth()) / 2);
                break;
            case RIGHT:
                titleLocation = dimensions.getBottomLeftPosition();
                titleLocation.sumX((dimensions.getWidth() - titleDimensions.getWidth()) / 2);
                titleLocation.sumY(-titleDimensions.getHeight());
                break;
            default:
                titleLocation = dimensions.getTopLeftPosition();
                titleLocation.sumX((dimensions.getWidth() - titleDimensions.getWidth()) / 2);
                titleLocation.sumY(titleDimensions.getHeight());
        }

        switch (direction) {
            case UP:
                MiscDrawer.drawString(graphics, title, titleLocation, titleSettings, -90);
                break;
            case DOWN:
                MiscDrawer.drawString(graphics, title, titleLocation, titleSettings, 90);
                break;
            default:
                MiscDrawer.drawString(graphics, title, titleLocation, titleSettings, 0);
        }
    }
}
