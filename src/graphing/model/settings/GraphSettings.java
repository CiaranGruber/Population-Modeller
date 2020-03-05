package graphing.model.settings;

import java.awt.*;

public class GraphSettings extends GraphicalSettings {
    private TextSettings titleSettings;

    public GraphSettings() {
        this(true);
    }

    public GraphSettings(boolean show) {
        this(new Color(0, 0, 0, 0), new TextSettings(new Font(Font.SANS_SERIF, Font.BOLD, 15)), show);
    }

    public GraphSettings(TextSettings titleSettings) {
        this(Color.BLACK, titleSettings, true);
    }

    public GraphSettings(Color backgroundColour, TextSettings titleSettings, boolean show) {
        super(backgroundColour, show);
        this.titleSettings = titleSettings;
    }

    public Color getBackgroundColour() {
        return getColour();
    }

    public void setBackgroundColour(Color colour) {
        setColour(colour);
    }

    public TextSettings getTitleSettings() {
        return titleSettings;
    }

    public void setTitleSettings(TextSettings titleSettings) {
        this.titleSettings = titleSettings;
    }
}
