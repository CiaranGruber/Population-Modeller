package graphing.view.tests;

import graphing.model.settings.GridLineSettings;
import graphing.model.settings.LineSettings;
import graphing.view.Direction;
import graphing.view.GridLineDrawer;
import position.ScaledDimension;
import position.ScaledPosition;

import javax.swing.*;
import java.awt.*;

public class TestGridLineDrawer extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.add(new TestGridLineDrawer());
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        LineSettings majorLineSettings = new LineSettings(Color.YELLOW, new BasicStroke(1), true);
        LineSettings minorLineSettings = new LineSettings(Color.BLACK, new BasicStroke(1), true);
        GridLineSettings gridLineSettings = new GridLineSettings(majorLineSettings, minorLineSettings, 50, 5, true);
        ScaledDimension dimensions = new ScaledDimension(new ScaledPosition(100, 100), 200, 150);
        GridLineDrawer.draw((Graphics2D) g, gridLineSettings, Direction.UP, dimensions);

        dimensions = new ScaledDimension(new ScaledPosition(100, 300), 200, 100);
        GridLineDrawer.draw((Graphics2D) g, new GridLineSettings(), Direction.RIGHT, dimensions);
    }
}
