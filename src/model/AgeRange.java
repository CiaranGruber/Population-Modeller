package model;

import mathematics.operable.OperableNumber;

public class AgeRange {
    private String range;
    private OperableNumber startingPopulation;
    private OperableNumber birthRate;
    private OperableNumber survivalRate;

    public AgeRange(String range, OperableNumber startingPopulation, OperableNumber birthRate, OperableNumber survivalRate) {
        this.range = range;
        this.startingPopulation = startingPopulation;
        this.birthRate = birthRate;
        this.survivalRate = survivalRate;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public OperableNumber getStartingPopulation() {
        return startingPopulation;
    }

    public void setStartingPopulation(OperableNumber startingPopulation) {
        this.startingPopulation = startingPopulation;
    }

    public OperableNumber getBirthRate() {
        return birthRate;
    }

    public void setBirthRate(OperableNumber birthRate) {
        this.birthRate = birthRate;
    }

    public OperableNumber getSurvivalRate() {
        return survivalRate;
    }

    public void setSurvivalRate(OperableNumber survivalRate) {
        this.survivalRate = survivalRate;
    }
}
