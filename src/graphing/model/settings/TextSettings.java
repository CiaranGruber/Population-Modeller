package graphing.model.settings;

import java.awt.*;

public class TextSettings extends GraphicalSettings {
    private Font font;

    public TextSettings() {
        this(true);
    }

    public TextSettings(Font font) {
        this(Color.BLACK, font);
    }

    public TextSettings(Color colour) {
        this(colour, true);
    }

    public TextSettings(boolean show) {
        this(Color.BLACK, show);
    }

    public TextSettings(Font font, boolean show) {
        this(Color.BLACK, font, show);
    }

    public TextSettings(Color colour, boolean show) {
        this(colour, new Font(Font.SANS_SERIF, Font.BOLD, 12), show);
    }

    public TextSettings(Color colour, Font font) {
        this(colour, font, true);
    }

    public TextSettings(Color colour, Font font, boolean show) {
        super(colour, show);
        this.font = font;
    }

    public TextSettings(TextSettings otherSettings) {
        super(otherSettings);
        font = new Font(otherSettings.getFont().getName(), otherSettings.getFont().getStyle(), otherSettings.getFont().getSize());
    }

    public Color getColour() {
        return super.getColour();
    }

    public void setColour(Color colour) {
        super.setColour(colour);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
