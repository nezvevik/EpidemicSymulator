package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.controllers.SimulationController;
import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.UISettings;
import javafx.application.Application;
import javafx.stage.Stage;

public class EpidemicSimulatorApp extends Application {
    @Override
    public void start(Stage stage) {
        //settings
        SimulationSettings simulationSettings = new SimulationSettings();
        UISettings uiSettings = new UISettings(400, 400, 10, 20);

        //adjust settings

        //starting simulation
        SimulationModel simulationModel = new SimulationModel(simulationSettings, uiSettings);
        SimulationController simulationController = new SimulationController(simulationModel, uiSettings);
        simulationController.runSimulation(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}