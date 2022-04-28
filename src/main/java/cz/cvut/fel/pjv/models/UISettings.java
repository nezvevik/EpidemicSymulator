package cz.cvut.fel.pjv.models;

public class UISettings {
    private final int simulationWidth;
    private final int simulationHeight;

    public final double personSize;
    public final double borderSize;

    public UISettings(int simulationWidth, int simulationHeight, double personSize, double borderSize) {
        this.simulationWidth = simulationWidth;
        this.simulationHeight = simulationHeight;
        this.personSize = personSize;
        this.borderSize = borderSize;
    }

    public int getSimulationWidth() {
        return simulationWidth;
    }

    public int getSimulationHeight() {
        return simulationHeight;
    }

    public double getPersonSize() {
        return personSize;
    }

    public double getBorderSize() {
        return borderSize;
    }
}
