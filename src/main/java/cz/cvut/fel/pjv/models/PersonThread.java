package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.SimulationModel;
import cz.cvut.fel.pjv.handlers.SimulationCanvasHandler;
import cz.cvut.fel.pjv.models.person.InfectionPhase;
import cz.cvut.fel.pjv.models.person.Person;

public class PersonThread extends Thread{
    private final Person person;
    private final SimulationModel simulationModel;
    private final SimulationCanvasHandler simulationCanvasHandler;

    public PersonThread(Person person, SimulationModel simulationModel, SimulationCanvasHandler simulationCanvasHandler) {
        super();
        this.person = person;
        this.simulationModel = simulationModel;
        this.simulationCanvasHandler = simulationCanvasHandler;
    }

    @Override
    public void run() {
        if (person.getInfectionPhase() != InfectionPhase.DECEASED) {
            simulationModel.updatePosition(person);
            if (person.getInfectionPhase() != InfectionPhase.CURED) {
                simulationModel.updateInfection(person);
            }
        }
        simulationCanvasHandler.drawPerson(person);

    }
}
