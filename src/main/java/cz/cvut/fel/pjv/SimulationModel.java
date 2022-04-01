package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.models.person.BasePerson;
import cz.cvut.fel.pjv.models.SimulationSettings;

import java.util.ArrayList;
import java.util.List;

public class SimulationModel {
    private List<BasePerson> people = new ArrayList<BasePerson>();

    private final SimulationSettings simulationSettings;

    public SimulationModel(SimulationSettings simulationSettings) {
        this.simulationSettings = simulationSettings;
    }

    public void runSimulation() {

    }


}
