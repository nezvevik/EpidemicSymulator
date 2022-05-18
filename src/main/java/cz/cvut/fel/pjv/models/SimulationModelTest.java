package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.models.person.InfectionPhase;
import cz.cvut.fel.pjv.models.person.Person;
import javafx.geometry.Point2D;

import static org.junit.Assert.*;

public class SimulationModelTest {
    private SimulationModel simulationModel;

    @org.junit.Before
    public void setUp() throws Exception {
        simulationModel = new SimulationModel(new SimulationSettings(),new UISettings(500, 500, 10, 20));
        simulationModel.initSimulationModel();
    }

    @org.junit.Test
    public void updatePositionTest1() {
        Person person = new Person(new Point2D(100, 100), new Point2D(1, 0),InfectionPhase.HEALTHY, true);
        simulationModel.updatePosition(person);
        assertTrue(person.getPosition().getX() == 101 && person.getPosition().getY() == 100);
    }

    @org.junit.Test
    public void updatePositionTest2() {
        Person person = new Person(new Point2D(495, 100), new Point2D(10, 0),InfectionPhase.HEALTHY, true);
        simulationModel.updatePosition(person);
        assertTrue(person.getPosition().getX() == 485 && person.getPosition().getY() == 100);
    }

    @org.junit.Test
    public void updatePositionTest3() {
        Person person = new Person(new Point2D(100, 495), new Point2D(0, 10),InfectionPhase.HEALTHY, true);
        simulationModel.updatePosition(person);
        assertTrue(person.getPosition().getX() == 100 && person.getPosition().getY() == 485);
    }

    @org.junit.Test
    public void updatePositionTest4() {
        Person person = new Person(new Point2D(100, 5), new Point2D(0, -10),InfectionPhase.HEALTHY, true);
        simulationModel.updatePosition(person);
        assertTrue(person.getPosition().getX() == 100 && person.getPosition().getY() == 15);
    }
    @org.junit.Test
    public void updatePositionTest5() {
        Person person = new Person(new Point2D(5, 100), new Point2D(-10, 0),InfectionPhase.HEALTHY, true);
        simulationModel.updatePosition(person);
        assertTrue(person.getPosition().getX() == 15 && person.getPosition().getY() == 100);
    }
}