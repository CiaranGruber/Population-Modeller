package mathematics.operable;

import java.math.BigDecimal;
import java.math.BigInteger;

public class LosslessNumber extends OperableNumber {
    private BigDecimal leadingSection;
    private BigDecimal recurringDecimal;

    public LosslessNumber() {
        this(BigDecimal.ZERO);
    }

    public LosslessNumber(long number) {
        this(BigDecimal.valueOf(number));
    }

    public LosslessNumber(double number) {
        this(BigDecimal.valueOf(number));
    }

    public LosslessNumber(BigInteger number) {
        this(new BigDecimal(number.toString()));
    }

    public LosslessNumber(BigDecimal number) {
        this(number, "0");
    }

    public LosslessNumber(long leadingSection, String recurringDecimal) {
        this(BigDecimal.valueOf(leadingSection), recurringDecimal);
    }

    public LosslessNumber(double leadingSection, String recurringDecimal) {
        this(BigDecimal.valueOf(leadingSection), recurringDecimal);
    }

    public LosslessNumber(BigDecimal leadingSection, String recurringDecimal) {
        this.leadingSection = leadingSection;
        this.recurringDecimal = new BigDecimal("0." + recurringDecimal);
    }

    public LosslessNumber(OperableNumber number) {
        LosslessNumber losslessNumber = number.toLosslessNumber();
        leadingSection = new BigDecimal(losslessNumber.leadingSection.toString());
        recurringDecimal = new BigDecimal(losslessNumber.recurringDecimal.toString());
    }

    public BigDecimal getLeadingSection() {
        return leadingSection;
    }

    public void setLeadingSection(double leadingSection) {
        this.leadingSection = BigDecimal.valueOf(leadingSection);
    }

    public void setLeadingSection(BigDecimal leadingSection) {
        this.leadingSection = leadingSection;
    }

    public void setRecurringDecimal(String recurringDecimal) {
        this.recurringDecimal = new BigDecimal("0." + recurringDecimal);
    }

    public String getRecurringDecimalString() {
        if (recurringDecimal.equals(BigDecimal.ZERO)) {
            return "0";
        } else {
            return recurringDecimal.toPlainString().substring(2);
        }
    }

    public Fraction toFraction() {
        int recurLength;
        BigDecimal leadingSection;

        recurLength = getRecurringDecimalString().length();
        if (this.leadingSection.scale() > 0) {
            leadingSection = new BigDecimal(this.leadingSection.toPlainString() +
                    getRecurringDecimalString()).scaleByPowerOfTen(recurLength);
        } else {
            leadingSection = new BigDecimal(this.leadingSection.toPlainString() + "." +
                    getRecurringDecimalString()).scaleByPowerOfTen(recurLength);
        }

        BigDecimal numerator = leadingSection.subtract(this.leadingSection);
        BigDecimal denominator = BigDecimal.valueOf(10).pow(recurLength).subtract(BigDecimal.ONE);
        denominator = denominator.scaleByPowerOfTen(numerator.scale());
        return new Fraction(numerator.unscaledValue(), denominator.toBigInteger());
    }

    @Override
    public LosslessNumber toLosslessNumber() {
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(leadingSection.toString());
        if (!recurringDecimal.equals(BigDecimal.ZERO)) {
            if (stringBuilder.indexOf(".") == -1) {
                stringBuilder.append(".");
            }
            stringBuilder.append("(").append(getRecurringDecimalString()).append(")");
        }
        return stringBuilder.toString();
    }

    @Override
    public OperableNumber add(OperableNumber o) {
        Fraction fraction = toFraction();
        return fraction.add(o).toLosslessNumber();
    }

    @Override
    public OperableNumber subtract(OperableNumber o) {
        Fraction fraction = toFraction();
        return fraction.subtract(o).toLosslessNumber();
    }

    @Override
    public OperableNumber multiply(OperableNumber o) {
        Fraction fraction = toFraction();
        return fraction.multiply(o).toLosslessNumber();
    }

    @Override
    public OperableNumber divide(OperableNumber o) {
        Fraction fraction = toFraction();
        return fraction.divide(o).toLosslessNumber();
    }

    @Override
    public OperableNumber pow(OperableNumber o) {
        Fraction fraction = toFraction();
        return fraction.pow(o).toLosslessNumber();
    }

    @Override
    public OperableNumber abs() {
        LosslessNumber number = new LosslessNumber(this);
        number.leadingSection = number.leadingSection.abs();
        return number;
    }
}
