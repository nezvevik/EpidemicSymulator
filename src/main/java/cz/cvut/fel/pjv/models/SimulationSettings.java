package cz.cvut.fel.pjv.models;

public class SimulationSettings {
    private boolean isGraph;

    private int obedientPopulation;
    private int disobedientPopulation;

    private double movementSpeed;

    private float infectionProbability;
    private double infectionRange;
    private int incubationPeriod;
    private float mortality;

    private boolean isMask;
    private float maskEfficiency;

    private boolean isDistancing;
    private int socialDistancingRange;

    //constructor
    public SimulationSettings() {
        this.isGraph = true;
        this.obedientPopulation = 30;
        this.disobedientPopulation = 20;

        this.movementSpeed = 1.5;

        this.infectionProbability = 0.2F;
        this.infectionRange = 20;
        this.incubationPeriod = 5000;
        this.mortality = 0.5F;

        this.isMask = false;
        this.maskEfficiency = 0;

        this.isDistancing = false;
        this.socialDistancingRange = 10;
    }

    public SimulationSettings(int obedientPopulation, int disobedientPopulation, int incubationPeriod, double infectionProbability, double infectionRange,  double mortality, double movementSpeed, boolean
            isMask, double maskEfficiency, boolean isDistancing, double socialDistancingRange) {
        this.obedientPopulation = obedientPopulation;
        this.disobedientPopulation = disobedientPopulation;
        this.movementSpeed =  movementSpeed;
        this.infectionProbability = (float) (infectionProbability / 100.0);
        this.infectionRange = infectionRange;
        this.incubationPeriod = incubationPeriod * 1000; //received in seconds thus has to be converted to ms
        this.mortality = (float) (mortality / 100.0);

        this.isMask = isMask;
        this.maskEfficiency = (float) (maskEfficiency / 100.0);
        this.isDistancing = isDistancing;
        this.socialDistancingRange = (int) socialDistancingRange;
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

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setInfectionProbability(float infectionProbability) {
        this.infectionProbability = infectionProbability;
    }

    public void setInfectionRange(int infectionRange) {
        this.infectionRange = infectionRange;
    }

    public void setIncubationPeriod(int incubationPeriod) {
        this.incubationPeriod = incubationPeriod;
    }

    public void setMortality(float mortality) {
        this.mortality = mortality;
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

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public float getInfectionProbability() {
        return infectionProbability;
    }

    public double getInfectionRange() {
        return infectionRange;
    }

    public int getIncubationPeriod() {
        return incubationPeriod;
    }

    public float getMortality() {
        return mortality;
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
