package graphing.model.settings;

import java.awt.*;

public class AxisSettings {
    private TextSettings axisTitleSettings;
    private TextSettings pointsTextSettings;
    private LineSettings pointsSettings;
    private LineSettings axisLineSettings;
    private double axisGap;
    private boolean show;

    public AxisSettings() {
        this(true);
    }

    public AxisSettings(boolean show) {
        axisTitleSettings = new TextSettings();
        Font font = axisTitleSettings.getFont();
        axisTitleSettings.setFont(font.deriveFont(Font.BOLD, (int) (font.getSize() * 1.5)));
        pointsTextSettings = new TextSettings(font.deriveFont((float) (font.getSize() * 0.8)));
        pointsSettings = new LineSettings();
        axisLineSettings = new LineSettings();
        this.axisGap = 15;
        this.show = show;
    }

    public AxisSettings(TextSettings textSettings, LineSettings lineSettings, double axisGap) {
        Font textFont = textSettings.getFont();
        Font titleFont = new Font(textFont.getName(), textFont.getStyle(), (int) (textFont.getSize() * 1.5));
        axisTitleSettings = new TextSettings(textSettings.getColour(), titleFont, textSettings.isShow());
        pointsTextSettings = textSettings;
        pointsSettings = lineSettings;
        axisLineSettings = lineSettings;
        this.axisGap = axisGap;
        show = true;
    }

    public AxisSettings(TextSettings axisTitleSettings, TextSettings pointsTextSettings, LineSettings pointsSettings,
                        LineSettings axisLineSettings, double axisGap, boolean show) {
        this.axisTitleSettings = axisTitleSettings;
        this.pointsTextSettings = pointsTextSettings;
        this.pointsSettings = pointsSettings;
        this.axisLineSettings = axisLineSettings;
        this.axisGap = axisGap;
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public LineSettings getAxisLineSettings() {
        return axisLineSettings;
    }

    public void setAxisLineSettings(LineSettings axisLineSettings) {
        this.axisLineSettings = axisLineSettings;
    }

    public double getAxisGap() {
        return axisGap;
    }

    public void setAxisGap(double axisGap) {
        this.axisGap = axisGap;
    }

    public TextSettings getAxisTitleSettings() {
        return axisTitleSettings;
    }

    public void setAxisTitleSettings(TextSettings axisTitleSettings) {
        this.axisTitleSettings = axisTitleSettings;
    }

    public TextSettings getPointsTextSettings() {
        return pointsTextSettings;
    }

    public void setPointsTextSettings(TextSettings pointsTextSettings) {
        this.pointsTextSettings = pointsTextSettings;
    }

    public LineSettings getPointsSettings() {
        return pointsSettings;
    }

    public void setPointsSettings(LineSettings pointsSettings) {
        this.pointsSettings = pointsSettings;
    }
}
