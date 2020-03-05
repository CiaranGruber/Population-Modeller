package mathematics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class MathUtil {
    public static byte intAt(double number, int index) {
        return intAt(BigDecimal.valueOf(number), index);
    }

    public static byte intAt(BigInteger number, int index) {
        return Byte.parseByte(Character.toString(number.toString().charAt(index)));
    }

    public static byte intAt(BigDecimal number, int index) {
        if (number.toString().indexOf('.') <= index) {
            return Byte.parseByte(Character.toString(number.toString().charAt(index + 1)));
        }
        return Byte.parseByte(Character.toString(number.toString().charAt(index)));
    }

    public static int length(long number) {
        return Long.toString(number).length();
    }

    public static int length(double number) {
        return length(BigDecimal.valueOf(number));
    }

    public static int length(BigInteger number) {
        return number.toString().length();
    }

    public static int length(BigDecimal number) {
        if (number.toString().contains(".")) {
            return number.toString().length() - 1;
        }
        return number.toString().length();
    }

    public static int preDecimalLength(double number) {
        return Double.toString(number).indexOf('.');
    }

    public static int preDecimalLength(BigDecimal number) {
        return number.precision() - number.scale();
    }

    public static int foreDecimalLength(double number) {
        return length(number) - Double.toString(number).indexOf('.');
    }

    public static int foreDecimalLength(BigDecimal number) {
        return number.scale();
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        while (b.compareTo(BigInteger.ZERO) > 0) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    public static int gcd(int... numbers) {
        long[] longNumbers = Arrays.stream(numbers).mapToLong(x -> (long) x).toArray();
        return (int) gcd(longNumbers);
    }

    public static long gcd(long... numbers) {
        BigInteger[] longNumbers = Arrays.stream(numbers).mapToObj(BigInteger::valueOf).toArray(BigInteger[]::new);
        return gcd(longNumbers).longValue();
    }

    public static BigInteger gcd(BigInteger... numbers) {
        BigInteger result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = gcd(result, numbers[i]);
        }
        return result;
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b.divide(gcd(a, b)));
    }

    public static int lcm(int... numbers) {
        long[] longNumbers = Arrays.stream(numbers).mapToLong(x -> (long) x).toArray();
        return (int) lcm(longNumbers);
    }

    public static long lcm(long... numbers) {
        BigInteger[] longNumbers = Arrays.stream(numbers).mapToObj(BigInteger::valueOf).toArray(BigInteger[]::new);
        return lcm(longNumbers).longValue();
    }

    public static BigInteger lcm(BigInteger... numbers) {
        BigInteger result = numbers[0];
        for (int i = 1; i < numbers.length; ++i) {
            result = lcm(result, numbers[i]);
        }
        return result;
    }
}
