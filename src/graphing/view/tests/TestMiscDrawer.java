package graphing.view.tests;

import graphing.model.settings.LineSettings;
import graphing.model.settings.MarkerSettings;
import graphing.model.settings.ShapeType;
import graphing.view.MiscDrawer;
import position.ScaledPosition;

import javax.swing.*;
import java.awt.*;

public class TestMiscDrawer extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.add(new TestMiscDrawer());
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        LineSettings borderSettings = new LineSettings(new Color(0, 0, 0), new BasicStroke(3), true);
        MarkerSettings markerSettings = new MarkerSettings(Color.ORANGE, borderSettings, 10, true, ShapeType.SQUARE, true);
        ScaledPosition position = new ScaledPosition(100, 100);
        MiscDrawer.drawMarker((Graphics2D) g, position, markerSettings);
    }
}
