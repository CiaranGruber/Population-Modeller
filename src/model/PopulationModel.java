package model;

import mathematics.Matrix;
import mathematics.operable.Fraction;

import java.util.ArrayList;

public class PopulationModel {
    private ArrayList<AgeRange> ageRanges;

    public PopulationModel() {
        this.ageRanges = new ArrayList<>();
    }

    public PopulationModel(ArrayList<AgeRange> ageRanges) {
        this.ageRanges = ageRanges;
    }

    public void addAgeRange(AgeRange ageRange) {
        ageRanges.add(ageRange);
    }

    public void addAgeRange(int index, AgeRange ageRange) {
        ageRanges.add(index, ageRange);
    }

    public ArrayList<AgeRange> getAgeRanges() {
        return ageRanges;
    }

    public Matrix generateExpectedPopulation(int years) {
        return generateLeslieMatrix().power(years).multiply(generatePopulationMatrix());
    }

    public Matrix generatePopulationMatrix() {
        Matrix matrix = new Matrix(ageRanges.size(), 1);
        for (int row = 0; row < ageRanges.size(); ++row) {
            matrix.setCell(row, 0, new Fraction(ageRanges.get(row).getStartingPopulation()));
        }
        return matrix;
    }

    public Matrix generateLeslieMatrix() {
        Matrix matrix = new Matrix(ageRanges.size());
        for (int i = 0; i < ageRanges.size() - 1; ++i) {
            matrix.setCell(0, i, ageRanges.get(i).getBirthRate().toFraction());
            matrix.setCell(1 + i, i, ageRanges.get(i).getSurvivalRate().toFraction());
        }
        matrix.setCell(0, ageRanges.size() - 1, ageRanges.get(ageRanges.size() - 1).getBirthRate());
        return matrix;
    }
}
