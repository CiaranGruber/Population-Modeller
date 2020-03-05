package mathematics.operable;

import mathematics.MathUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Fraction extends OperableNumber {
    private BigInteger numerator;
    private BigInteger denominator;

    public Fraction(long numerator) {
        this(numerator, 1);
    }

    public Fraction(double number) {
        this(BigDecimal.valueOf(number));
    }

    public Fraction(BigDecimal decimal) {
        LosslessNumber losslessNumber = new LosslessNumber(decimal);
        Fraction fraction = losslessNumber.toFraction();

        numerator = fraction.numerator;
        denominator = fraction.denominator;
        simplify();
    }

    public Fraction(long numerator, long denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public Fraction(long numerator, BigInteger denominator) {
        this(BigInteger.valueOf(numerator), denominator);
    }

    public Fraction(BigInteger numerator, long denominator) {
        this(numerator, BigInteger.valueOf(denominator));
    }

    public Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction(Fraction fraction) {
        fraction.numerator = new BigInteger(fraction.numerator.toString());
        fraction.denominator = new BigInteger(fraction.denominator.toString());
    }

    public Fraction(OperableNumber number) {
        Fraction fraction = number.toFraction();
        numerator = new BigInteger(fraction.numerator.toString());
        denominator = new BigInteger(fraction.denominator.toString());
    }

    public BigInteger getNumerator() {
        return new BigInteger(numerator.toString());
    }

    public void setNumerator(long numerator) {
        this.numerator = BigInteger.valueOf(numerator);
    }

    public void setNumerator(BigInteger numerator) {
        this.numerator = numerator;
    }

    public BigInteger getDenominator() {
        return new BigInteger(denominator.toString());
    }

    public void setDenominator(long denominator) {
        this.denominator = BigInteger.valueOf(denominator);
    }

    public void setDenominator(BigInteger denominator) {
        this.denominator = denominator;
    }

    public void simplify() {
        BigInteger gcd = numerator.gcd(denominator);
        numerator = numerator.divide(gcd);
        denominator = denominator.divide(gcd);
    }

    public void changeDenominator(BigInteger newDenom) {
        Fraction fraction = new Fraction(newDenom, denominator);
        setDenominator(newDenom);
        setNumerator(fraction.multiply(new LosslessNumber(getNumerator())).toBigInteger());
    }

    public LosslessNumber toLosslessNumber() {
        LosslessNumber number = new LosslessNumber();
        StringBuilder result = new StringBuilder();
        StringBuilder carryOverNumber = new StringBuilder();
        ArrayList<String> carryOversDone = new ArrayList<>();
        String stringNumerator = numerator.toString();
        boolean isNegative = false;

        if (denominator.equals(BigInteger.ZERO)) {
            return new LosslessNumber(0);
        }

        // Perform division upon numerator without decimal points
        for (int i = 0; i < stringNumerator.length(); ++i) {
            // Test if carry over number and numerator is divisible
            BigInteger numerator;
            if (stringNumerator.charAt(i) == '-') {
                numerator = new BigInteger(Character.toString(stringNumerator.charAt(i + 1)));
                i++;
            } else {
                numerator = new BigInteger(carryOverNumber.toString() + stringNumerator.charAt(i));
            }
            BigInteger[] divisorAndRemainder = numerator.divideAndRemainder(denominator);

            carryOverNumber = new StringBuilder(divisorAndRemainder[1].toString());
            result.append(divisorAndRemainder[0]);
        }

        result.append(".");

        // Perform division upon remainder
        while (!carryOverNumber.toString().equals("0") && carryOversDone.size() < 100) {
            // Test if carry over number and numerator is divisible
            if (carryOversDone.contains(carryOverNumber.toString())) {
                break;
            }
            carryOversDone.add(carryOverNumber.toString());

            BigInteger numerator = new BigInteger(carryOverNumber.toString() + "0");
            BigInteger[] divisorAndRemainder = numerator.divideAndRemainder(denominator);

            carryOverNumber = new StringBuilder(divisorAndRemainder[1].toString());
            result.append(divisorAndRemainder[0]);
        }

        // If carry over number is not 0, a recurring decimal has been found
        if (!carryOverNumber.toString().equals("0") && carryOversDone.size() < 100) {
            String reccuringDecimal;
            int carryOver = 0;
            while (!carryOverNumber.toString().equals(carryOversDone.get(carryOver))) {
                carryOver++;
            }

            // Get the recurring decimal
            reccuringDecimal = result.substring(result.length() - carryOversDone.size() + carryOver);
            number.setRecurringDecimal(reccuringDecimal);

            // Remove unnecessary parts
            result.delete(result.length() - carryOversDone.size() + carryOver, result.length());
        }

        number.setLeadingSection(new BigDecimal(result.toString()));
        return number;
    }

    @Override
    public Fraction toFraction() {
        return this;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public OperableNumber add(OperableNumber o) {
        Fraction other = o.toFraction();
        BigInteger lcm = MathUtil.lcm(denominator, other.denominator);
        changeDenominator(lcm);
        other.changeDenominator(lcm);
        numerator = numerator.add(other.numerator);
        simplify();
        return this;
    }

    @Override
    public OperableNumber subtract(OperableNumber o) {
        Fraction other = o.toFraction();
        BigInteger lcm = MathUtil.lcm(denominator, other.denominator);
        changeDenominator(lcm);
        other.changeDenominator(lcm);
        numerator = numerator.subtract(other.numerator);
        simplify();
        return this;
    }

    @Override
    public OperableNumber multiply(OperableNumber o) {
        Fraction other = o.toFraction();
        numerator = numerator.multiply(other.numerator);
        denominator = denominator.multiply(other.denominator);
        simplify();
        return this;
    }

    @Override
    public OperableNumber divide(OperableNumber o) {
        Fraction other = o.toFraction();
        numerator = numerator.multiply(other.denominator);
        denominator = denominator.multiply(other.numerator);
        simplify();
        return this;
    }

    @Override
    public OperableNumber pow(OperableNumber o) {
        return null;
    }

    @Override
    public OperableNumber abs() {
        Fraction newFrac = new Fraction(this);
        newFrac.numerator = newFrac.numerator.abs();
        return newFrac;
    }
}
