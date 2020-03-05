package graphing.model;

import org.javatuples.Pair;

import java.awt.*;
import java.util.ArrayList;

public class Legend {
    ArrayList<Pair<Color, String>> mainColourToData;

    public Legend() {
        this(new ArrayList<>());
    }

    public Legend(ArrayList<Pair<Color, String>> mainColourToData) {
        this.mainColourToData = mainColourToData;
    }

    public void addToLegend(Color dataColour, String string) {
        mainColourToData.add(new Pair<>(dataColour, string));
    }

    public ArrayList<Pair<Color, String>> getMainColourToData() {
        return mainColourToData;
    }

    public void setMainColourToData(ArrayList<Pair<Color, String>> mainColourToData) {
        this.mainColourToData = mainColourToData;
    }
}
