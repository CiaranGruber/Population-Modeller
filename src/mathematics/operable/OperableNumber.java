package mathematics.operable;

import mathematics.CustomNumber;

public abstract class OperableNumber extends CustomNumber {
    public abstract OperableNumber add(OperableNumber o);

    public abstract OperableNumber subtract(OperableNumber o);

    public abstract OperableNumber multiply(OperableNumber o);

    public abstract OperableNumber divide(OperableNumber o);

    public abstract OperableNumber pow(OperableNumber o);

    public abstract OperableNumber abs();

    public OperableNumber max(OperableNumber o) {
        if (compareTo(o) >= 0) {
            return this;
        } else {
            return o;
        }
    }

    public OperableNumber min(OperableNumber o) {
        if (compareTo(o) <= 0) {
            return this;
        } else {
            return o;
        }
    }
}
