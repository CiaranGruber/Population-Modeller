package graphing.model.settings;

import java.awt.*;

public class GridLineSettings {
    private LineSettings majorLines;
    private LineSettings minorLines;
    private int minorCount;
    private int majorGap;
    private boolean show;

    public GridLineSettings() {
        this(new LineSettings(new Color(13, 13, 13, 129), new BasicStroke(2), true),
                new LineSettings(new Color(102, 102, 102, 215), new BasicStroke(1), true),
                50, 5, true);
    }

    public GridLineSettings(boolean show) {
        this(new LineSettings(false), new LineSettings(false), 50, 0, show);
    }

    public GridLineSettings(LineSettings majorLines, LineSettings minorLines, int majorGap, int minorCount, boolean show) {
        this.majorLines = majorLines;
        this.minorLines = minorLines;
        this.majorGap = majorGap;
        this.minorCount = minorCount;
        this.show = show;
    }

    public int getMinorCount() {
        return minorCount;
    }

    public void setMinorCount(int minorCount) {
        this.minorCount = minorCount;
    }

    public int getMajorGap() {
        return majorGap;
    }

    public void setMajorGap(int majorGap) {
        this.majorGap = majorGap;
    }

    public LineSettings getMajorLines() {
        return majorLines;
    }

    public void setMajorLines(LineSettings majorLines) {
        this.majorLines = majorLines;
    }

    public LineSettings getMinorLines() {
        return minorLines;
    }

    public void setMinorLines(LineSettings minorLines) {
        this.minorLines = minorLines;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
