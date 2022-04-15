package cz.cvut.fel.pjv.models.person;

import javafx.scene.paint.Color;

public enum InfectionPhase {
    HEALTHY(Color.GREEN),
    INFECTED(Color.RED),
    DECEASED(Color.GRAY),
    CURED(Color.DARKBLUE);
    private final Color color;

    InfectionPhase(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
