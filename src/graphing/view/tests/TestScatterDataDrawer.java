package graphing.view.tests;

import graphing.model.scatter.ScatterData;
import graphing.model.scatter.ScatterDataPoint;
import graphing.model.settings.LineSettings;
import graphing.model.settings.MarkerSettings;
import graphing.model.settings.ShapeType;
import graphing.view.ScatterDataDrawer;
import mathematics.operable.LosslessNumber;
import mathematics.operable.OperableNumber;
import mathematics.operable.Range;
import position.ScaledDimension;
import position.ScaledPosition;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TestScatterDataDrawer extends JPanel {
    public static ScatterData generateScatterData(MarkerSettings markerSettings, LineSettings lineSettings,
                                                  Range<OperableNumber> xAxisRange, Range<OperableNumber> yAxisRange) {
        Random random = new Random();
        ScatterData scatterData = new ScatterData("Scatter", 12892, lineSettings, markerSettings);
        for (int i = 0; i < random.nextInt(10); ++i) {
            scatterData.addDataPoint(generateScatterDataPoint(xAxisRange, yAxisRange));
        }
        scatterData.addDataPoint(new ScatterDataPoint(7, 30));
        scatterData.addDataPoint(new ScatterDataPoint(8, 5));
        scatterData.addDataPoint(new ScatterDataPoint(13, 80, new MarkerSettings(new Color(255, 6, 85, 255),
                new LineSettings(), 15, true, ShapeType.SQUARE, true)));
        scatterData.addDataPoint(new ScatterDataPoint(17, 57));
        return scatterData;
    }

    private static ScatterDataPoint generateScatterDataPoint(Range<OperableNumber> xAxisRange, Range<OperableNumber> yAxisRange) {
        Random random = new Random();
        int xValue = random.nextInt(xAxisRange.getRange().intValue()) + xAxisRange.getMinValue().intValue();
        int yValue = random.nextInt(yAxisRange.getRange().intValue()) + yAxisRange.getMinValue().intValue();
        return new ScatterDataPoint(xValue, yValue);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.add(new TestScatterDataDrawer());
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        LineSettings lineSettings = new LineSettings(new Color(0, 137, 255, 255), new BasicStroke(2), true);
        LineSettings border = new LineSettings(new Color(0, 255, 143, 255), new BasicStroke(1), true);
        MarkerSettings markerSettings = new MarkerSettings(new Color(255, 159, 0, 255), border, 10,
                true, ShapeType.CIRCLE, true);
        Range<OperableNumber> xAxisRange = new Range<>(new LosslessNumber(0), new LosslessNumber(20));
        Range<OperableNumber> yAxisRange = new Range<>(new LosslessNumber(0), new LosslessNumber(100));
        ScatterData scatterData = generateScatterData(markerSettings, lineSettings, xAxisRange, yAxisRange);

        ScaledDimension dimensions = new ScaledDimension(new ScaledPosition(100, 100), 200, 100);
        ScatterDataDrawer.draw((Graphics2D) g, scatterData, xAxisRange, yAxisRange, dimensions);
    }
}
