package graphing.model;

import graphing.model.settings.AxisSettings;
import mathematics.operable.OperableNumber;
import mathematics.operable.Range;

public class Axis {
    private String axisTitle;
    private AxisSettings axisSettings;
    private Range<OperableNumber> range;

    public Axis(String axisTitle, Range<OperableNumber> range) {
        this.axisTitle = axisTitle;
        axisSettings = new AxisSettings();
        this.range = range;
    }

    public Axis(String axisTitle, AxisSettings axisSettings, Range<OperableNumber> range) {
        this.axisTitle = axisTitle;
        this.axisSettings = axisSettings;
        this.range = range;
    }

    public String getAxisTitle() {
        return axisTitle;
    }

    public void setAxisTitle(String axisTitle) {
        this.axisTitle = axisTitle;
    }

    public Range<OperableNumber> getRange() {
        return range;
    }

    public void setRange(Range<OperableNumber> range) {
        this.range = range;
    }

    public AxisSettings getAxisSettings() {
        return axisSettings;
    }

    public void setAxisSettings(AxisSettings axisSettings) {
        this.axisSettings = axisSettings;
    }
}
