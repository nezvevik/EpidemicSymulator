package cz.cvut.fel.pjv.models;

public class SimulationSettings {
    private boolean isGraph;

    private int obedientPopulation;
    private int disobedientPopulation;

    private float movementSpeed;

    private float infectionProbability;
    private float infectionRange;
    private int incubationPeriod;
    private float mortality;

    private boolean isQuarantine;
    private double quarantineProbability;

    private boolean isMask;
    private float maskEfficiency;

    private boolean isDistancing;
    private int socialDistancingRange;

    //constructor
    public SimulationSettings() {
        this.isGraph = false;
        this.obedientPopulation = 10;
        this.disobedientPopulation = 10;

        this.movementSpeed = 1.5F;

        this.infectionProbability = 1.0F;
        this.infectionRange = 30;
        this.incubationPeriod = 0;
        this.mortality = 0;

        this.isQuarantine = false;
        this.quarantineProbability = 0;

        this.isMask = false;
        this.maskEfficiency = 0;

        this.isDistancing = false;
        this.socialDistancingRange = 0;
    }

    //setter
    public void setGraph(boolean graph) {
        isGraph = graph;
    }

    public void setObedientPopulation(int obedientPopulation) {
        this.obedientPopulation = obedientPopulation;
    }

    public void setDisobedientPopulation(int disobedientPopulation) {
        this.disobedientPopulation = disobedientPopulation;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setInfectionProbability(float infectionProbability) {
        this.infectionProbability = infectionProbability;
    }

    public void setInfectionRange(float infectionRange) {
        this.infectionRange = infectionRange;
    }

    public void setIncubationPeriod(int incubationPeriod) {
        this.incubationPeriod = incubationPeriod;
    }

    public void setMortality(float mortality) {
        this.mortality = mortality;
    }

    public void setQuarantine(boolean quarantine) {
        isQuarantine = quarantine;
    }

    public void setQuarantineProbability(int quarantineProbability) {
        this.quarantineProbability = quarantineProbability;
    }

    public void setMask(boolean mask) {
        isMask = mask;
    }

    public void setMaskEfficiency(float maskEfficiency) {
        this.maskEfficiency = maskEfficiency;
    }

    public void setDistancing(boolean distancing) {
        isDistancing = distancing;
    }

    public void setSocialDistancingRange(int socialDistancingRange) {
        this.socialDistancingRange = socialDistancingRange;
    }


    //getter
    public boolean isGraph() {
        return isGraph;
    }

    public int getObedientPopulation() {
        return obedientPopulation;
    }

    public int getDisobedientPopulation() {
        return disobedientPopulation;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public float getInfectionProbability() {
        return infectionProbability;
    }

    public float getInfectionRange() {
        return infectionRange;
    }

    public int getIncubationPeriod() {
        return incubationPeriod;
    }

    public float getMortality() {
        return mortality;
    }

    public boolean isQuarantine() {
        return isQuarantine;
    }

    public double getQuarantineProbability() {
        return quarantineProbability;
    }

    public boolean isMask() {
        return isMask;
    }

    public float getMaskEfficiency() {
        return maskEfficiency;
    }

    public boolean isDistancing() {
        return isDistancing;
    }

    public int getSocialDistancingRange() {
        return socialDistancingRange;
    }
}
