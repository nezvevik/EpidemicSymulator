package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.handlers.SimulationCanvasHandler;
import cz.cvut.fel.pjv.models.person.BasePerson;
import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.person.DisobedientPerson;
import cz.cvut.fel.pjv.models.person.InfectionPhase;
import cz.cvut.fel.pjv.models.person.ObedientPerson;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationModel {
    private List<BasePerson> people = new ArrayList<BasePerson>();
    private final Point2D bound = new Point2D(400, 400);

    private final SimulationSettings simulationSettings;

    public SimulationModel(SimulationSettings simulationSettings) {
        this.simulationSettings = simulationSettings;
        Random rand = new Random();
        for (int i = 0; i < simulationSettings.getObedientPopulation(); i++) {
            people.add(new ObedientPerson(new Point2D(rand.nextInt((int)bound.getX()),rand.nextInt((int)bound.getY())), new Point2D(1, 1), InfectionPhase.HEALTHY));
        }
        for (int i = 0; i < simulationSettings.getDisobedientPopulation(); i++) {
            people.add(new DisobedientPerson(new Point2D(rand.nextInt((int)bound.getX()),rand.nextInt((int)bound.getY())), new Point2D(1, 1), InfectionPhase.HEALTHY));
        }

    }

    public void runSimulation() {
        SimulationCanvasHandler simulationCanvasHandler = new SimulationCanvasHandler();
        simulationCanvasHandler.draw(this);
    }

    public List<BasePerson> getPeople() {
        return people;
    }

    public SimulationSettings getSimulationSettings() {
        return simulationSettings;
    }
}
