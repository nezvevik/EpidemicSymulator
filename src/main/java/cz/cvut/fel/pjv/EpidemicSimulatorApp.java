package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.handlers.SimulationCanvasHandler;
import cz.cvut.fel.pjv.models.SimulationSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EpidemicSimulatorApp extends Application {

    @Override
    public void start(Stage stage) {
        //settings
        SimulationSettings simulationSettings = new SimulationSettings();

        //adjust settings

        //starting simulation
        SimulationModel simulationModel = new SimulationModel(simulationSettings);
        simulationModel.runSimulation(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}