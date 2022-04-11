package cz.cvut.fel.pjv.models.person;

import javafx.geometry.Point2D;

public abstract class BasePerson {
    private Point2D position;
    private Point2D direction;

    private InfectionPhase infectionPhase;

    public abstract boolean isMask();
    public abstract boolean isQuarantine();
    public abstract boolean isDistancing();

    public BasePerson(Point2D position, Point2D direction, InfectionPhase infectionPhase) {
        this.position = position;
        this.direction = direction;
        this.infectionPhase = infectionPhase;
    }

    public Point2D getPosition() {
        return position;
    }

    public Point2D getDirection() {
        return direction;
    }

    public InfectionPhase getInfectionPhase() {
        return infectionPhase;
    }
}
