package graphing.model.scatter;

import graphing.model.AbstractGraph;
import graphing.model.Legend;
import graphing.model.settings.ScatterGraphSettings;

public class ScatterGraph extends AbstractGraph<ScatterPlot, ScatterGraphSettings> {
    private Legend legend;

    public ScatterGraph(String title, ScatterPlot scatterPlot, ScatterGraphSettings graphSettings) {
        super(title, scatterPlot, graphSettings);
        setLegend();
    }

    @Override
    public void setGraphPlot(ScatterPlot graphPlot) {
        super.setGraphPlot(graphPlot);
        setLegend();
    }

    public void setLegend() {
        legend = new Legend();
        for (ScatterData scatterData : getGraphPlot().getDataSets()) {
            legend.addToLegend(scatterData.getLineSettings().getColour(), scatterData.getDataName());
        }
    }

    public Legend getLegend() {
        setLegend();
        return legend;
    }
}
