package graphing.view.tests;

import graphing.model.Axis;
import graphing.model.settings.AxisSettings;
import graphing.model.settings.LineSettings;
import graphing.model.settings.TextSettings;
import graphing.view.AxisDrawer;
import graphing.view.Direction;
import mathematics.operable.LosslessNumber;
import mathematics.operable.OperableNumber;
import mathematics.operable.Range;
import position.ScaledDimension;
import position.ScaledPosition;

import javax.swing.*;
import java.awt.*;

public class TestAxisDrawer extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.add(new TestAxisDrawer());
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int axisGap = 20;
        TextSettings axisTitleSettings = new TextSettings(Color.BLUE, new Font("Algerian", Font.BOLD, 15), true);
        TextSettings pointsTextSettings = new TextSettings(Color.RED, new Font(Font.SANS_SERIF, Font.PLAIN, 8), true);
        LineSettings axisLineSettings = new LineSettings(Color.GREEN, new BasicStroke(1), true);
        LineSettings pointsLineSettings = new LineSettings(Color.ORANGE, new BasicStroke(2), true);
        AxisSettings axisSettings = new AxisSettings(axisTitleSettings, pointsTextSettings, pointsLineSettings, axisLineSettings,
                axisGap, true);
        Range<OperableNumber> range = new Range<>(new LosslessNumber(5), new LosslessNumber(15));
        Axis axis = new Axis("Test axis", axisSettings, range);
        ScaledDimension dimension = new ScaledDimension(new ScaledPosition(100, 100), 60, 200);

        AxisDrawer.draw((Graphics2D) g, Direction.DOWN, axis, dimension);
    }
}
