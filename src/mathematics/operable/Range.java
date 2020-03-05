package mathematics.operable;

public class Range<T extends OperableNumber> {
    private T minValue;
    private T maxValue;

    public Range(T value1, T value2) {
        if (value1.compareTo(value2) > 0) {
            maxValue = value1;
            minValue = value2;
        } else {
            maxValue = value2;
            minValue = value1;
        }
    }

    public boolean contains(T value) {
        return value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0;
    }

    public OperableNumber getRange() {
        return maxValue.subtract(minValue);
    }

    public void replaceMaxValue(T newValue) {
        if (newValue.compareTo(minValue) > 0) {
            maxValue = newValue;
        } else {
            maxValue = minValue;
            minValue = newValue;
        }
    }

    public T getMaxValue() {
        return maxValue;
    }

    public void replaceMinValue(T newValue) {
        if (newValue.compareTo(maxValue) < 0) {
            minValue = newValue;
        } else {
            minValue = maxValue;
            maxValue = newValue;
        }
    }

    public T getMinValue() {
        return minValue;
    }
}
