package graphing.view.tests;

import graphing.model.Axis;
import graphing.model.scatter.ScatterData;
import graphing.model.scatter.ScatterPlot;
import graphing.model.settings.*;
import graphing.view.ScatterPlotDrawer;
import mathematics.operable.LosslessNumber;
import mathematics.operable.OperableNumber;
import mathematics.operable.Range;
import position.ScaledDimension;
import position.ScaledPosition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TestScatterPlotDrawer extends JPanel {
    public static ArrayList<ScatterData> generateScatterPlotData(Range<OperableNumber> xAxisRange, Range<OperableNumber> yAxisRange) {
        Random random = new Random();
        ArrayList<ScatterData> scatterDataList = new ArrayList<>();
        for (int i = 0; i < random.nextInt(4) + 1; ++i) {
            ShapeType shapeType = ShapeType.values()[random.nextInt(ShapeType.values().length)];
            MarkerSettings markerSettings = new MarkerSettings(Color.GREEN, generateLineSettings(), random.nextInt(15),
                    random.nextBoolean(), shapeType, random.nextBoolean());
            LineSettings lineSettings = generateLineSettings();
            ScatterData scatterData = TestScatterDataDrawer.generateScatterData(markerSettings, lineSettings, xAxisRange, yAxisRange);
            scatterDataList.add(scatterData);
        }
        return scatterDataList;
    }

    private static LineSettings generateLineSettings() {
        Random random = new Random();
        Color colour = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        return new LineSettings(colour, new BasicStroke(random.nextInt(5)), random.nextBoolean());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.add(new TestScatterPlotDrawer());
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int axisGap = 10;
        TextSettings axisTitleSettings = new TextSettings(Color.GREEN, new Font("Arial", Font.BOLD, 15), true);
        TextSettings pointsTextSettings = new TextSettings(Color.BLACK, new Font("Arial", Font.BOLD, 8), true);
        LineSettings axisLineSettings = new LineSettings(Color.GRAY, new BasicStroke(1), true);
        LineSettings pointsSettings = new LineSettings(Color.GRAY, new BasicStroke(1), true);
        AxisSettings axisSettings = new AxisSettings(axisTitleSettings, pointsTextSettings, pointsSettings, axisLineSettings, axisGap, true);
        Range<OperableNumber> xAxisRange = new Range<>(new LosslessNumber(0), new LosslessNumber(24));
        Axis xAxis = new Axis("X-Axis", axisSettings, xAxisRange);

        axisTitleSettings = new TextSettings(Color.GREEN, new Font("Arial", Font.BOLD, 15), true);
        pointsTextSettings = new TextSettings(Color.BLACK, new Font("Arial", Font.BOLD, 8), true);
        axisLineSettings = new LineSettings(Color.YELLOW, new BasicStroke(1), true);
        pointsSettings = new LineSettings(Color.GRAY, new BasicStroke(1), true);
        axisSettings = new AxisSettings(axisTitleSettings, pointsTextSettings, pointsSettings, axisLineSettings, axisGap, true);
        Range<OperableNumber> yAxisRange = new Range<>(new LosslessNumber(0), new LosslessNumber(24));
        Axis yAxis = new Axis("Y-Axis", axisSettings, yAxisRange);

        GridLineSettings gridLineSettings = new GridLineSettings(generateLineSettings(), generateLineSettings(), 50, 5, true);
        ScatterPlotSettings scatterSettings = new ScatterPlotSettings(gridLineSettings);

        ScatterPlot scatterPlot = new ScatterPlot(generateScatterPlotData(xAxis.getRange(), yAxis.getRange()),
                xAxis, yAxis, scatterSettings);

        ScaledDimension dimensions = new ScaledDimension(new ScaledPosition(50, 50), 400, 400);
        ScatterPlotDrawer.draw((Graphics2D) g, scatterPlot, dimensions);
    }
}
