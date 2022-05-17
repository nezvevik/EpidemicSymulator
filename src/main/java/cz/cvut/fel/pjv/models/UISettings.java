package cz.cvut.fel.pjv.models;

public class UISettings {
    private final double simulationWidth;
    private final double simulationHeight;

    public final double personSize;
    public final double borderSize;

    public UISettings(double simulationWidth, double simulationHeight, double personSize, double borderSize) {
        this.simulationWidth = simulationWidth;
        this.simulationHeight = simulationHeight;
        this.personSize = personSize;
        this.borderSize = borderSize;
    }

    public double getSimulationWidth() {
        return simulationWidth;
    }

    public double getSimulationHeight() {
        return simulationHeight;
    }

    public double getPersonSize() {
        return personSize;
    }

    public double getBorderSize() {
        return borderSize;
    }
}
