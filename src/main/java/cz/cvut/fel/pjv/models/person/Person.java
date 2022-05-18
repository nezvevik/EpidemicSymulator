package cz.cvut.fel.pjv.models.person;

import javafx.geometry.Point2D;

public class Person {
    private Point2D position;
    private Point2D direction;
    private Point2D originPosition;

    private InfectionPhase infectionPhase;

    private final boolean isObedient;

    public Person(Point2D position, Point2D direction, InfectionPhase infectionPhase, boolean isObedient) {
        this.position = position;
        this.originPosition = position;
        this.direction = direction;
        this.infectionPhase = infectionPhase;
        this.isObedient = isObedient;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }

    public void setInfectionPhase(InfectionPhase infectionPhase) {
        this.infectionPhase = infectionPhase;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setOriginPosition(Point2D originPosition) {
        this.originPosition = originPosition;
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

    public boolean isObedient() {
        return isObedient;
    }

    public Point2D getOriginPosition() {
        return originPosition;
    }
}
