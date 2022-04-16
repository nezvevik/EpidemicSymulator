package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.SimulationModel;
import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.person.Person;
import cz.cvut.fel.pjv.models.person.InfectionPhase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

import static cz.cvut.fel.pjv.SimulationModel.*;

public class SimulationCanvasHandler {
    private GraphicsContext context;
    private float infectionRange;

    public SimulationCanvasHandler(GraphicsContext context, float infectionRange) {
        this.context = context;
        this.infectionRange = infectionRange;
    }
    public void draw(List<Person> people) {
        clearCanvas();
        people.forEach(person -> {
            drawPerson(person);
        });
    }
    private void drawPerson(Person person) {
        if (person.getInfectionPhase() == InfectionPhase.INFECTED) {
            context.setFill(Color.PALEVIOLETRED);
            context.fillOval(person.getPosition().getX() - infectionRange/2, person.getPosition().getY() - infectionRange/2, infectionRange, infectionRange);
        }

        context.setFill(person.getInfectionPhase().getColor());
        if (person.isObedient()) {
            context.fillOval(person.getPosition().getX() - SIZE/2, person.getPosition().getY() - SIZE/2, SIZE, SIZE);
        } else {
            context.fillRect(person.getPosition().getX() - SIZE/2, person.getPosition().getY() - SIZE/2, SIZE, SIZE);
        }
    }

    private void clearCanvas() {
        context.setFill(Color.WHITE);
        context.fillRect(0,0, WIDTH, HEIGHT);
    }
}
