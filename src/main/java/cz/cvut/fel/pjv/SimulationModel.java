package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.handlers.SimulationCanvasHandler;
import cz.cvut.fel.pjv.models.person.Person;
import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.person.InfectionPhase;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.PI;

public class SimulationModel {
    public static double WIDTH = 400;
    public static double HEIGHT = 400;

    public static final double SIZE = 10;
    public static final double BORDER = 20;


    private List<Person> people = new ArrayList<Person>();

    private final SimulationSettings simulationSettings;

    public SimulationModel(SimulationSettings simulationSettings) {
        this.simulationSettings = simulationSettings;
        for (int i = 0; i < simulationSettings.getObedientPopulation() + simulationSettings.getDisobedientPopulation(); i++) {
            //setting movement vector
            Random rand = new Random();
            double moveAngle = rand.nextDouble()*2*PI;
//            double moveRadius =simulationSettings.getMovementSpeed();
            double moveRadius = 0;
            Point2D moveVector = new Point2D(Math.cos(moveAngle) * moveRadius, Math.sin(moveAngle) *moveRadius);

            //adding people to list
            boolean isObedient;
            if (i < simulationSettings.getObedientPopulation()) {
                isObedient = true;
            } else {
                isObedient = false;
            }
            //-10
            people.add(new Person(new Point2D(SIZE + BORDER + rand.nextDouble() * (WIDTH - SIZE - 2 * BORDER),SIZE + BORDER + rand.nextDouble() * (HEIGHT - SIZE - 2 * BORDER)), moveVector, InfectionPhase.HEALTHY, isObedient));

            //setting patient number 0
            people.get(0).setInfectionPhase(InfectionPhase.INFECTED);
        }

    }

    public void runSimulation() {

        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas();
        GraphicsContext context = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        canvas.setWidth(WIDTH);
        canvas.setHeight(HEIGHT);

        SimulationCanvasHandler simulationCanvasHandler = new SimulationCanvasHandler(context, simulationSettings.getInfectionRange());

        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                simulationCanvasHandler.draw(people);
            }
        };
        timer.start();
    }

    private void update() {
        people.forEach(person1 -> {
//            updatePosition(person1);
            if (person1.getInfectionPhase() == InfectionPhase.INFECTED) {
                Random rand = new Random();
                people.forEach(person2 -> {
                    if (person2.getInfectionPhase() == InfectionPhase.HEALTHY) {
                        if (isInRange(person1, person2)) {
                            if(isTransmitted(rand)) {
                                transmit(person2);
                            }
                        }
                    }
                });
            }
        });
    }

    private void updatePosition (Person person) {
        double x = person.getPosition().getX();
        double y = person.getPosition().getY();

        double vx = person.getDirection().getX();
        double vy = person.getDirection().getY();

        if (x <=  SIZE ) {
            person.setDirection(new Point2D(-vx, vy));
            vx = -vx;
            person.setPosition(new Point2D(SIZE, y + vy));
        } else if (x  + SIZE >= WIDTH) {
            person.setDirection(new Point2D(-vx, vy));
            vx = -vx;
            person.setPosition(new Point2D(WIDTH - SIZE, y + vy));
        } else if (y <=  SIZE) {
            person.setDirection(new Point2D(vx, -vy));
            vy = -vy;
            person.setPosition(new Point2D(x + vx, SIZE));
        } else if (y  + SIZE >= HEIGHT) {
            person.setDirection(new Point2D(vx, -vy));
            vy = -vy;
            person.setPosition(new Point2D(x + vx, HEIGHT - SIZE));
        }
        person.setPosition(new Point2D(x + vx, y + vy));
    }

    private boolean isInRange(Person person1, Person person2) {
        boolean ret = false;
        double distance = Math.pow(person1.getPosition().getX() - person2.getPosition().getX(), 2) + Math.pow(person1.getPosition().getY() - person2.getPosition().getY(), 2);
        System.out.println(distance);
        if (distance < Math.pow(simulationSettings.getInfectionRange(), 2)) {
            ret = true;
        }
        System.out.println(ret);
        return ret;
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

    public static double getWIDTH() {
        return WIDTH;
    }

    public static double getHEIGHT() {
        return HEIGHT;
    }
}
