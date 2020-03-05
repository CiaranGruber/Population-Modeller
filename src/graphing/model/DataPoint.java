package graphing.model;

public class DataPoint {
    private double value;

    public DataPoint(double value) {
        this.value = value;
    }

    protected double getValue() {
        return value;
    }

    protected void setValue(double value) {
        this.value = value;
    }
}
