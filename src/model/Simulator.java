package model;

import graphing.model.scatter.ScatterData;
import graphing.model.scatter.ScatterDataPoint;
import graphing.model.scatter.ScatterGraph;
import graphing.model.scatter.ScatterPlot;
import graphing.model.settings.LineSettings;
import graphing.model.settings.ScatterGraphSettings;
import mathematics.Matrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulator {
    public static ScatterGraph generateGraph(PopulationModel model, int years) {
        ScatterPlot scatterPlot = new ScatterPlot(new ArrayList<>(), "Years", "Population");
        ScatterGraph scatterGraph = new ScatterGraph("Simulated for " + years + " years", scatterPlot, new ScatterGraphSettings());

        Random random = new Random();
        ScatterData[] ageRanges = new ScatterData[model.getAgeRanges().size()];
        // Get age ranges
        for (int i = 0; i < model.getAgeRanges().size(); ++i) {
            LineSettings lineSettings = new LineSettings(new Color(random.nextInt(256), random.nextInt(256),
                    random.nextInt(256)), new BasicStroke(2));
            ageRanges[i] = new ScatterData(model.getAgeRanges().get(i).getRange(), scatterPlot.getUniqueID(), lineSettings);
            scatterPlot.addDataSet(ageRanges[i]);
        }

        // Model population growth
        Matrix yearModel = model.generatePopulationMatrix();
        Matrix leslieMatrix = model.generateLeslieMatrix();
//        Matrix cullingMatrix = new Matrix(5, 1);
//        int[] values = {46, 20, 16, 4, 1};
//        for (int i = 0; i < cullingMatrix.getRowCount(); ++i) {
//            cullingMatrix.setCell(i, 0, new Fraction(values[i]));
//        }
        for (int i = 1; i <= years; ++i) {
            for (int j = 0; j < ageRanges.length; ++j) {
                ScatterDataPoint scatterDataPoint = new ScatterDataPoint(i, yearModel.getCell(j, 0).doubleValue());
                ageRanges[j].addDataPoint(scatterDataPoint);
            }
            yearModel = leslieMatrix.multiply(yearModel/*.subtract(cullingMatrix)*/);
        }

        scatterGraph.setLegend();
        scatterPlot.resetAxes();

        return scatterGraph;
    }
}
