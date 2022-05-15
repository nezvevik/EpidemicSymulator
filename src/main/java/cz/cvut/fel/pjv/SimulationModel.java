package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.models.UISettings;
import cz.cvut.fel.pjv.models.person.Person;
import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.person.InfectionPhase;
import javafx.geometry.Point2D;

import java.util.*;

import static java.lang.Math.PI;

public class SimulationModel {

    private List<Person> people = new ArrayList<>();

    private final SimulationSettings simulationSettings;
    private final UISettings uiSettings;

    private int numOfHealthy;
    private int numOfInfected;

    public SimulationModel(SimulationSettings simulationSettings, UISettings uiSettings) {
        this.simulationSettings = simulationSettings;
        this.uiSettings = uiSettings;
    }

    public int getNumOfInfected() {
        return numOfInfected;
    }

    public int getNumOfHealthy() {
        return numOfHealthy;
    }

    public void initialize() {
        numOfHealthy = simulationSettings.getObedientPopulation() + simulationSettings.getDisobedientPopulation();
        for (int i = 0; i < numOfHealthy; i++) {
            //setting movement vector
            Random rand = new Random();
            double moveAngle = rand.nextDouble()*2*PI;

            double moveRadius = simulationSettings.getMovementSpeed();
            Point2D moveVector = new Point2D(Math.cos(moveAngle) * moveRadius, Math.sin(moveAngle) * moveRadius);

            // adding people to list
            boolean isObedient;
            if (i < simulationSettings.getObedientPopulation()) {
                isObedient = true;
            } else {
                isObedient = false;
            }
            people.add(new Person(new Point2D(uiSettings.getPersonSize() + uiSettings.getBorderSize() + rand.nextDouble() * (SimulationModel.this.uiSettings.getSimulationWidth() - SimulationModel.this.uiSettings.getPersonSize() - 2 * uiSettings.getBorderSize()),
                    SimulationModel.this.uiSettings.getPersonSize() + uiSettings.getBorderSize() + rand.nextDouble() * (SimulationModel.this.uiSettings.getSimulationHeight() - SimulationModel.this.uiSettings.getPersonSize() - 2 * uiSettings.getBorderSize())), moveVector, InfectionPhase.HEALTHY, isObedient));

        }

        //setting patient number 0
        people.get(0).setInfectionPhase(InfectionPhase.INFECTED);
        infectionTimer(people.get(0));
        numOfInfected = 1;
        numOfHealthy--;
    }

    public void updateInfection(Person person) {
        if (person.getInfectionPhase() != InfectionPhase.INFECTED) return;
        Random rand = new Random();
        people.forEach(person2 -> {
            if (person2.getInfectionPhase() != InfectionPhase.HEALTHY) return;
            if (!isInRange(person, person2, getSimulationSettings().getInfectionRange())) return;
            if (!isTransmitted(rand)) return;
            numOfHealthy--;
            numOfInfected++;
            transmit(person2);
            infectionTimer(person2);
        });
    }

    private void infectionTimer(Person person) {
        Timer timer = new Timer();
        Random rand = new Random();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (simulationSettings.getMortality() >= rand.nextFloat()) {
                    person.setInfectionPhase(InfectionPhase.DECEASED);
                } else {
                    person.setInfectionPhase(InfectionPhase.CURED);
                }
                numOfInfected--;
            }
        };
        timer.schedule(task, simulationSettings.getIncubationPeriod());
    }

    public void updatePosition(Person person) {
        double x = person.getPosition().getX();
        double y = person.getPosition().getY();

        double vx = person.getDirection().getX();
        double vy = person.getDirection().getY();

        // check position against bounds
        if (x <= uiSettings.getPersonSize()) {
            person.setDirection(new Point2D(-vx, vy));
            vx = -vx;
            person.setPosition(new Point2D(uiSettings.getPersonSize(), y + vy));
        } else if (x  + uiSettings.getPersonSize() >= uiSettings.getSimulationWidth()) {
            person.setDirection(new Point2D(-vx, vy));
            vx = -vx;
            person.setPosition(new Point2D(uiSettings.getSimulationWidth() - uiSettings.getPersonSize(), y + vy));
        } else if (y <=  uiSettings.getPersonSize()) {
            person.setDirection(new Point2D(vx, -vy));
            vy = -vy;
            person.setPosition(new Point2D(x + vx, uiSettings.getPersonSize()));
        } else if (y  + uiSettings.getPersonSize() >= uiSettings.getSimulationHeight()) {
            person.setDirection(new Point2D(vx, -vy));
            vy = -vy;
            person.setPosition(new Point2D(x + vx, uiSettings.getSimulationHeight() - uiSettings.getPersonSize()));
        }

        person.setPosition(new Point2D(x + vx, y + vy));
    }

    private boolean isInRange(Person person1, Person person2, float range) {
        boolean ret = false;
        double distance = getDistance(person1, person2);
        if (distance < Math.pow(range, 2)) {
            ret = true;
        }
        return ret;
    }

    private double getDistance(Person person1, Person person2) {
        return Math.pow(person1.getPosition().getX() - person2.getPosition().getX(), 2) + Math.pow(person1.getPosition().getY() - person2.getPosition().getY(), 2);
    }

    private boolean isTransmitted(Random random) {
        boolean ret = false;
        float chance = random.nextFloat();
        if (chance < simulationSettings.getInfectionProbability()) { ret = true; }
        return ret;
    }

    private void transmit(Person person) {
        person.setInfectionPhase(InfectionPhase.INFECTED);
    }

    public List<Person> getPeople() {
        return people;
    }

    public SimulationSettings getSimulationSettings() {
        return simulationSettings;
    }

}
