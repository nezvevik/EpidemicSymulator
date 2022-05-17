package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.controllers.SettingsController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class EpidemicSimulatorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // initializing settings


        SettingsController settingsController= new SettingsController();
        settingsController.initSettingsStage(stage);



//        //adjust settings window
//        SettingsController settingsController = new SettingsController(simulationSettings, uiSettings);
//        settingsController.initializeSettingsController(stage);

//        starting simulation window
//        SimulationModel simulationModel = new SimulationModel(simulationSettings, uiSettings);
//        SimulationController simulationController = new SimulationController(simulationModel, uiSettings);
//        simulationController.runSimulation(stage);

    }

    public static void main(String[] args) {
        launch();
    }

}