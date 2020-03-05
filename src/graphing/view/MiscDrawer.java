package graphing.view;

import graphing.model.settings.LineSettings;
import graphing.model.settings.MarkerSettings;
import graphing.model.settings.TextSettings;
import position.BasicPosition;
import position.ScaledDimension;

import java.awt.*;

public class MiscDrawer {
    public static void drawLine(Graphics2D graphics, BasicPosition position, BasicPosition secondPosition, LineSettings lineSettings) {
        if (!lineSettings.isShow()) {
            return;
        }

        // Get previous settings
        Color prevColour = graphics.getColor();
        Stroke prevStroke = graphics.getStroke();

        graphics.setColor(lineSettings.getColour());
        graphics.setStroke(lineSettings.getStroke());

        graphics.drawLine(position.getIntX(), position.getIntY(), secondPosition.getIntX(), secondPosition.getIntY());

        // Reset settings
        graphics.setColor(prevColour);
        graphics.setStroke(prevStroke);
    }

    public static void drawString(Graphics2D graphics, String string, BasicPosition position, TextSettings textSettings) {
        drawString(graphics, string, position, textSettings, 0);
    }

    public static void drawString(Graphics2D graphics, String string, BasicPosition position, TextSettings textSettings, int angle) {
        if (!textSettings.isShow()) {
            return;
        }

        // Get previous settings
        Color prevColour = graphics.getColor();
        Font prevFont = graphics.getFont();

        graphics.setColor(textSettings.getColour());
        graphics.setFont(textSettings.getFont());

        drawRotate(graphics, string, position.getIntX(), position.getIntY(), angle);

        // Reset settings
        graphics.setColor(prevColour);
        graphics.setFont(prevFont);
    }

    private static void drawRotate(Graphics2D g, String text, double x, double y, int angle) {
        g.translate((float) x, (float) y);
        g.rotate(Math.toRadians(angle));
        g.drawString(text, 0, 0);
        g.rotate(-Math.toRadians(angle));
        g.translate(-(float) x, -(float) y);
    }

    public static void fillRect(Graphics2D graphics, Color colour, ScaledDimension dimensions) {
        // Get previous settings
        Color prevColour = graphics.getColor();

        graphics.setColor(colour);
        graphics.fillRect((int) dimensions.getMinX(), (int) dimensions.getMinY(), (int) dimensions.getWidth(), (int) dimensions.getHeight());

        // Reset settings
        graphics.setColor(prevColour);
    }

    public static void drawMarker(Graphics2D graphics, BasicPosition position, MarkerSettings markerSettings) {
        if (!markerSettings.isShow()) {
            return;
        }

        // Get previous settings
        Color prevColour = graphics.getColor();
        Stroke prevStroke = graphics.getStroke();

        BasicPosition newPos = new BasicPosition(position).sumX(-markerSettings.getSize() / 2.0).sumY(-markerSettings.getSize() / 2.0);

        if (markerSettings.getBorder().isShow()) {
            graphics.setColor(markerSettings.getBorder().getColour());
            graphics.setStroke(markerSettings.getBorder().getStroke());
            switch (markerSettings.getShapeType()) {
                case CIRCLE:
                    graphics.drawOval(newPos.getIntX(), newPos.getIntY(), markerSettings.getSize(), markerSettings.getSize());
                    break;
                case SQUARE:
                    graphics.drawRect(newPos.getIntX(), newPos.getIntY(), markerSettings.getSize(), markerSettings.getSize());
            }
        }
        if (markerSettings.isFilled()) {
            graphics.setColor(markerSettings.getFillColour());
            switch (markerSettings.getShapeType()) {
                case CIRCLE:
                    graphics.fillOval(newPos.getIntX(), newPos.getIntY(), markerSettings.getSize(), markerSettings.getSize());
                    break;
                case SQUARE:
                    graphics.fillRect(newPos.getIntX(), newPos.getIntY(), markerSettings.getSize(), markerSettings.getSize());
            }
        }

        // Reset settings
        graphics.setColor(prevColour);
        graphics.setStroke(prevStroke);
    }
}
