package mathematics;

import mathematics.operable.Fraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMatrix {
    @Test
    void power() {
        Matrix matrix = new Matrix(3);
        int a = 1;
        for (int i = 0; i < matrix.getRowCount(); ++i) {
            for (int j = 0; j < matrix.getColumnCount(); ++j) {
                matrix.setCell(i, j, new Fraction(a));
                a++;
            }
        }

        Matrix ansMatrix = matrix.power(0);
        assertEquals(Matrix.getIdentityMatrix(3), ansMatrix, "Identity test failed");
        ansMatrix = matrix.power(1);
        assertEquals(ansMatrix, matrix, "Power of 1 test failed");
        ansMatrix = matrix.power(2);
        Matrix expectedMatrix = new Matrix(3, 3);
        int[] values = {30, 36, 42, 66, 81, 96, 102, 126, 150};
        a = 0;
        for (int i = 0; i < matrix.getRowCount(); ++i) {
            for (int j = 0; j < matrix.getColumnCount(); ++j) {
                expectedMatrix.setCell(i, j, new Fraction(values[a]));
                a++;
            }
        }
        assertEquals(expectedMatrix, ansMatrix, "Power of 2 test failed");
        ansMatrix = matrix.power(3);
        expectedMatrix = new Matrix(3, 3);
        values = new int[]{468, 576, 684, 1062, 1305, 1548, 1656, 2034, 2412};
        a = 0;
        for (int i = 0; i < matrix.getRowCount(); ++i) {
            for (int j = 0; j < matrix.getColumnCount(); ++j) {
                expectedMatrix.setCell(i, j, new Fraction(values[a]));
                a++;
            }
        }
        assertEquals(expectedMatrix, ansMatrix, "Power of 2 test failed");
    }

    @Test
    void multiply() {
        Matrix matrix = new Matrix(3, 2);
        int a = 1;
        for (int i = 0; i < matrix.getRowCount(); ++i) {
            for (int j = 0; j < matrix.getColumnCount(); ++j) {
                matrix.setCell(i, j, new Fraction(a));
                a++;
            }
        }
        Matrix matrix2 = new Matrix(2, 1);
        a = 1;
        for (int i = 0; i < matrix2.getRowCount(); ++i) {
            for (int j = 0; j < matrix2.getColumnCount(); ++j) {
                matrix2.setCell(i, j, new Fraction(a));
                a++;
            }
        }
        Matrix ansMatrix = matrix.multiply(matrix2);
        Matrix expectedMatrix = new Matrix(3, 1);
        expectedMatrix.setCell(0, 0, new Fraction(5));
        expectedMatrix.setCell(1, 0, new Fraction(11));
        expectedMatrix.setCell(2, 0, new Fraction(17));
        assertEquals(expectedMatrix, ansMatrix, "Matrices not equal");
    }
}