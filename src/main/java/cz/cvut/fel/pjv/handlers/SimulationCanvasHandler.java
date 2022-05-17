package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.models.UISettings;
import cz.cvut.fel.pjv.models.person.Person;
import cz.cvut.fel.pjv.models.person.InfectionPhase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class SimulationCanvasHandler {
    private GraphicsContext context;
    private final UISettings uiSettings;
    private double infectionRange;


    public SimulationCanvasHandler(GraphicsContext context, UISettings uiSettings, double infectionRange) {
        this.context = context;
        this.uiSettings = uiSettings;
        this.infectionRange = infectionRange;
    }
    public void draw(List<Person> people) {
        clearCanvas();
        people.forEach(person -> {
            drawPerson(person);
        });
    }
    public void drawPerson(Person person) {
        if (person.getInfectionPhase() == InfectionPhase.INFECTED) {
            context.setFill(Color.PALEVIOLETRED);
            context.fillOval(person.getPosition().getX() - infectionRange, person.getPosition().getY() - infectionRange, infectionRange * 2, infectionRange * 2);
        }

        context.setFill(person.getInfectionPhase().getColor());
        if (person.isObedient()) {
            context.fillOval(person.getPosition().getX() - uiSettings.getPersonSize()/2, person.getPosition().getY() - uiSettings.getPersonSize()/2, uiSettings.getPersonSize(), uiSettings.getPersonSize());
        } else {
            context.fillRect(person.getPosition().getX() - uiSettings.getPersonSize()/2, person.getPosition().getY() - uiSettings.getPersonSize()/2, uiSettings.getPersonSize(), uiSettings.getPersonSize());
        }
    }

    public void clearCanvas() {
        context.setFill(Color.WHITE);
        context.fillRect(0,0, uiSettings.getSimulationWidth(), uiSettings.getSimulationHeight());
    }
}
