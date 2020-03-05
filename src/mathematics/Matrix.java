package mathematics;

import mathematics.exceptions.DifferentRowCountException;
import mathematics.exceptions.IncompatibleMatricesException;
import mathematics.exceptions.ValueOutOfRangeException;
import mathematics.operable.Fraction;
import mathematics.operable.LosslessNumber;
import mathematics.operable.OperableNumber;

import java.util.ArrayList;
import java.util.Objects;

public class Matrix {
    private ArrayList<ArrayList<OperableNumber>> rows;

    public Matrix(int squareDimensions) {
        this(squareDimensions, squareDimensions);
    }

    public Matrix(int rowCount, int columnCount) {
        rows = new ArrayList<>();
        for (int row = 0; row < rowCount; ++row) {
            rows.add(new ArrayList<>());
            for (int column = 0; column < columnCount; ++column) {
                rows.get(row).add(new Fraction(0));
            }
        }
    }

    public Matrix(ArrayList<ArrayList<OperableNumber>> rows) {
        this.rows = new ArrayList<>();
        int columnCount;
        if (rows.size() > 0) {
            columnCount = rows.get(0).size();
        } else {
            columnCount = 0;
        }
        for (ArrayList<OperableNumber> row : rows) {
            if (columnCount == row.size()) {
                throw new DifferentRowCountException("Different rows used");
            }
            this.rows.add(row);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new ArrayList<>();
        for (int row = 0; row < matrix.getRowCount(); ++row) {
            rows.add(new ArrayList<>());
            for (int column = 0; column < matrix.getColumnCount(); ++column) {
                rows.get(row).add(new Fraction(matrix.getCell(row, column)));
            }
        }
    }

    public static Matrix getIdentityMatrix(int size) {
        Matrix matrix = new Matrix(size);
        for (int i = 0; i < size; ++i) {
            matrix.setCell(i, i, new Fraction(1));
        }
        return matrix;
    }

    public static boolean isSquare(Matrix matrix) {
        return matrix.getRowCount() == matrix.getColumnCount();
    }

    public Matrix add(Matrix altMatrix) {
        if (getRowCount() != altMatrix.getRowCount() && getColumnCount() != altMatrix.getColumnCount()) {
            throw new IncompatibleMatricesException("Matrix dimensions are not equal");
        }
        Matrix matrix = new Matrix(getRowCount(), getColumnCount());
        for (int row = 0; row < matrix.getRowCount(); ++row) {
            for (int column = 0; column < matrix.getColumnCount(); ++column) {
                matrix.setCell(row, column, getCell(row, column).add(altMatrix.getCell(row, column)));
            }
        }
        return matrix;
    }

    public Matrix subtract(Matrix altMatrix) {
        if (getRowCount() != altMatrix.getRowCount() && getColumnCount() != altMatrix.getColumnCount()) {
            throw new IncompatibleMatricesException("Matrix dimensions are not equal");
        }
        Matrix matrix = new Matrix(getRowCount(), getColumnCount());
        for (int row = 0; row < matrix.getRowCount(); ++row) {
            for (int column = 0; column < matrix.getColumnCount(); ++column) {
                matrix.setCell(row, column, getCell(row, column).subtract(altMatrix.getCell(row, column)));
            }
        }
        return matrix;
    }

//    public Matrix power(long power) {
//        if (power < 0) {
//            throw new ValueOutOfRangeException("Power must be >= 0");
//        }
//        if (!isSquare(this)) {
//            throw new IncompatibleMatricesException("Number of rows does not equal the number of columns");
//        }
//
//        Matrix base = new Matrix(this);
//        int matrixSize = base.getRowCount();
//
//        Matrix ans = getIdentityMatrix(matrixSize);
//
//        // Binary exponentiation
//        while (power != 0) {
//            if ((power & 1) != 0) {
//                ans = ans.multiply(base);
//            }
//            base = base.multiply(base);
//            power >>= 1;
//        }
//
//        return ans;
//    }

    public Matrix multiply(int number) {
        Matrix matrix = new Matrix(getRowCount(), getColumnCount());
        for (int row = 0; row < matrix.getRowCount(); ++row) {
            for (int column = 0; column < matrix.getColumnCount(); ++column) {
                matrix.setCell(row, column, getCell(row, column).multiply(new LosslessNumber(number)));
            }
        }
        return matrix;
    }

    public Matrix multiply(Matrix altMatrix) {
        if (getColumnCount() != altMatrix.getRowCount()) {
            throw new IncompatibleMatricesException("Number of columns does not equal the alternate matrices' number of rows");
        }
        Matrix matrix = new Matrix(getRowCount(), altMatrix.getColumnCount());
        for (int i = 0; i < matrix.getRowCount(); ++i) {
            for (int j = 0; j < matrix.getColumnCount(); ++j) {
                OperableNumber value = new LosslessNumber(0);
                for (int x = 0; x < getColumnCount(); ++x) {
                    value = value.add(new Fraction(getCell(i, x)).multiply(altMatrix.getCell(x, j)));
                }
                matrix.setCell(i, j, value);
            }
        }
        return matrix;
    }

    public Matrix power(int power) {
        if (power < 0) {
            throw new ValueOutOfRangeException("Power must be >= 0");
        }
        if (!isSquare(this)) {
            throw new IncompatibleMatricesException("Number of rows does not equal the number of columns");
        }
        Matrix matrix = getIdentityMatrix(getRowCount());
        for (int i = 0; i < power; ++i) {
            matrix = matrix.multiply(this);
        }
        return matrix;
    }

    public OperableNumber getCell(int row, int column) {
        return rows.get(row).get(column);
    }

    public void setCell(int row, int column, OperableNumber value) {
        rows.get(row).set(column, value);
    }

    public int getColumnCount() {
        if (rows.size() > 0) {
            return rows.get(0).size();
        } else {
            return 0;
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;
        Matrix matrix = (Matrix) o;
        if (getRowCount() != matrix.getRowCount() || getColumnCount() != matrix.getColumnCount()) {
            return false;
        }
        for (int row = 0; row < matrix.getRowCount(); ++row) {
            for (int column = 0; column < matrix.getColumnCount(); ++column) {
                if (!getCell(row, column).equals(matrix.getCell(row, column))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }
}
