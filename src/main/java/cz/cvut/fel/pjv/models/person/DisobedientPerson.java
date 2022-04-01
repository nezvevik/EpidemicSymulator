package cz.cvut.fel.pjv.models.person;

import javafx.geometry.Point2D;

public class DisobedientPerson extends BasePerson {
    @Override
    public boolean isMask() {
        return true;
    }

    @Override
    public boolean isQuarantine() {
        return true;
    }

    @Override
    public boolean isDistancing() {
        return true;
    }

    public DisobedientPerson(Point2D position, Point2D direction, InfectionPhase infectionPhase) {
        super(position, direction, infectionPhase);
    }
}
