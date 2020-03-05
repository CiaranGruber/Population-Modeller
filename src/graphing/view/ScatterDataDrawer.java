package graphing.view;

import graphing.model.scatter.ScatterData;
import graphing.model.scatter.ScatterDataPoint;
import mathematics.operable.Fraction;
import mathematics.operable.LosslessNumber;
import mathematics.operable.OperableNumber;
import mathematics.operable.Range;
import position.BasicPosition;
import position.RestrictedLine;
import position.ScaledDimension;
import position.ScaledPosition;

import java.awt.*;

public class ScatterDataDrawer {
    public static void draw(Graphics2D graphics, ScatterData scatterData, Range<OperableNumber> xAxisRange,
                            Range<OperableNumber> yAxisRange, ScaledDimension dimensions) {
        double xScale = new Fraction(dimensions.getWidth()).divide(xAxisRange.getRange()).doubleValue();
        double yScale = new Fraction(dimensions.getHeight()).divide(yAxisRange.getRange()).doubleValue();
        // Draw lines
        for (int j = 0; j < scatterData.getData().size() - 1; ++j) {
            ScatterDataPoint dataPoint = scatterData.getData().get(j);
            ScatterDataPoint nextDataPoint = scatterData.getData().get(j + 1);
            ScaledPosition dataPos = dimensions.getBottomLeftPosition();
            dataPos.sumX(dataPoint.getXValue() * xScale).sumY(-dataPoint.getYValue() * yScale);
            ScaledPosition nextDataPos = dimensions.getBottomLeftPosition();
            nextDataPos.sumX(nextDataPoint.getXValue() * xScale).sumY(-nextDataPoint.getYValue() * yScale);
            if (dimensions.containsPoint(dataPos) || dimensions.containsPoint(nextDataPos)) {
                RestrictedLine line = new RestrictedLine(dataPos, nextDataPos);
                line = line.trimLine(dimensions);
                MiscDrawer.drawLine(graphics, line.getStartPos(), line.getEndPos(), scatterData.getLineSettings());
            }
        }

        // Draw markers
        for (ScatterDataPoint dataPoint : scatterData.getData()) {
            if (xAxisRange.contains(new LosslessNumber(dataPoint.getXValue())) && yAxisRange.contains(new LosslessNumber(dataPoint.getYValue()))) {
                BasicPosition dataPos = new BasicPosition(dataPoint.getXValue() * xScale, dataPoint.getYValue() * yScale);
                dataPos.sum(dimensions.getTopLeftPosition());
                MiscDrawer.drawMarker(graphics, dataPos, dataPoint.getMarkerSettings());
            }
        }
    }
}
