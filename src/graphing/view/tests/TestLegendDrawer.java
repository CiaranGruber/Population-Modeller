package graphing.view.tests;

import graphing.model.Legend;
import graphing.model.settings.*;
import graphing.view.LegendDrawer;
import position.ScaledDimension;
import position.ScaledPosition;

import javax.swing.*;
import java.awt.*;

public class TestLegendDrawer extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.add(new TestLegendDrawer());
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Legend legend = new Legend();
        legend.addToLegend(Color.GREEN, "Hello");
        legend.addToLegend(Color.ORANGE, "A test");
        legend.addToLegend(Color.RED, "More tests");

        Color backgroundColour = Color.GRAY;
        LineSettings mainBorder = new LineSettings(Color.BLACK, new BasicStroke(2), true);
        LineSettings markerBorder = new LineSettings(Color.YELLOW, new BasicStroke(2), true);
        MarkerSettings markerSettings = new MarkerSettings(Color.BLACK, markerBorder, 5, true, ShapeType.SQUARE, true);
        TextSettings textSettings = new TextSettings(Color.BLACK, new Font("Arial", Font.BOLD, 10), true);
        LegendSettings legendSettings = new LegendSettings(backgroundColour, mainBorder, markerSettings, textSettings, true);
        ScaledDimension dimensions = new ScaledDimension(new ScaledPosition(100, 100), 100, 100);
        LegendDrawer.draw((Graphics2D) g, legend, legendSettings, dimensions);

        dimensions = new ScaledDimension(new ScaledPosition(300, 100), 100, 100);
        LegendDrawer.draw((Graphics2D) g, legend, new LegendSettings(), dimensions);
    }
}
