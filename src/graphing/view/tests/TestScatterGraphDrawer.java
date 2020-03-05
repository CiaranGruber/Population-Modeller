package graphing.view.tests;

import graphing.model.Axis;
import graphing.model.scatter.ScatterGraph;
import graphing.model.scatter.ScatterPlot;
import graphing.model.settings.*;
import graphing.view.ScatterGraphDrawer;
import mathematics.operable.LosslessNumber;
import mathematics.operable.OperableNumber;
import mathematics.operable.Range;
import position.ScaledDimension;
import position.ScaledPosition;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TestScatterGraphDrawer extends JPanel {
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
        frame.add(new TestScatterGraphDrawer());
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

        ScatterPlot scatterPlot = new ScatterPlot(TestScatterPlotDrawer.generateScatterPlotData(xAxis.getRange(), yAxis.getRange()),
                xAxis, yAxis, scatterSettings);

        Color graphColour = new Color(197, 255, 0, 20);
        Color markerColour = new Color(0, 0, 0, 0);
        LineSettings border = new LineSettings(new Color(0, 0, 0, 0), new BasicStroke(1), true);
        LineSettings markerBorder = new LineSettings(new Color(0, 0, 0, 0), new BasicStroke(1), true);
        MarkerSettings markerSettings = new MarkerSettings(markerColour, markerBorder, 10, true, ShapeType.SQUARE, true);
        TextSettings textSettings = new TextSettings(new Color(0, 0, 0, 255),
                new Font("Arial", Font.BOLD, 8), true);
        LegendSettings legendSettings = new LegendSettings(new Color(255, 28, 12, 20), border, markerSettings, textSettings, true);

        TextSettings titleSettings = new TextSettings(Color.MAGENTA, new Font("Arial", Font.BOLD, 15), true);
        ScatterGraphSettings graphSettings = new ScatterGraphSettings(graphColour, titleSettings, legendSettings, scatterSettings, true);

        ScatterGraph scatterGraph = new ScatterGraph("Scatter Plot", scatterPlot, graphSettings);

        ScaledDimension dimensions = new ScaledDimension(new ScaledPosition(50, 50), 400, 400);
        ScatterGraphDrawer.draw((Graphics2D) g, scatterGraph, dimensions);
    }
}
