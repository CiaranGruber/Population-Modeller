package mathematics;

import mathematics.operable.Fraction;
import mathematics.operable.LosslessNumber;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

public abstract class CustomNumber extends Number implements Comparable<CustomNumber> {
    public BigDecimal toBigDecimal(int scale) {
        LosslessNumber number = toLosslessNumber();
        StringBuilder stringBuilder = new StringBuilder(number.getLeadingSection().toPlainString());
        if (!number.getRecurringDecimalString().equals("0") &&
                (stringBuilder.indexOf(".") != -1 || stringBuilder.length() - stringBuilder.indexOf(".") < scale)) {
            if (number.getLeadingSection().scale() <= 0) {
                stringBuilder.append(".");
            }
            int i = 0;
            while (stringBuilder.length() - stringBuilder.indexOf(".") - 1 <= scale) {
                stringBuilder.append(number.getRecurringDecimalString().charAt(i % number.getRecurringDecimalString().length()));
                i++;
            }
        }
        return new BigDecimal(stringBuilder.toString()).setScale(scale, RoundingMode.HALF_UP);
    }

    public BigInteger toBigInteger() {
        return toBigDecimal(0).toBigInteger();
    }

    public abstract LosslessNumber toLosslessNumber();

    public abstract Fraction toFraction();

    @Override
    public int intValue() {
        return (int) longValue();
    }

    @Override
    public long longValue() {
        return toBigDecimal(0).longValue();
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        return toBigDecimal(20).doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Number)) return false;

        Fraction fraction;
        Fraction thisFraction = toFraction();
        if (o instanceof CustomNumber) {
            fraction = ((CustomNumber) o).toFraction();
        } else if (o.getClass() == BigInteger.class) {
            fraction = (new LosslessNumber((BigInteger) o)).toFraction();
        } else if (o.getClass() == BigDecimal.class) {
            fraction = (new LosslessNumber((BigDecimal) o)).toFraction();
        } else {
            fraction = new LosslessNumber(((Number) o).doubleValue()).toFraction();
        }
        return thisFraction.getNumerator().equals(fraction.getNumerator()) && thisFraction.getDenominator().equals(fraction.getDenominator());
    }

    @Override
    public int hashCode() {
        Fraction fraction = toFraction();
        return Objects.hash(fraction.getNumerator(), fraction.getDenominator());
    }

    @Override
    public int compareTo(CustomNumber o) {
        Fraction thisFrac = toFraction();
        Fraction other = o.toFraction();
        if (equals(o)) {
            return 0;
        }
        BigInteger lcm = MathUtil.lcm(thisFrac.getDenominator(), other.getDenominator());
        thisFrac.changeDenominator(lcm);
        other.changeDenominator(lcm);
        return (thisFrac.getNumerator().compareTo(other.getNumerator()));
    }
}
