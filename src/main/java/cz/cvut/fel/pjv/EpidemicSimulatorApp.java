package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.controllers.ChartController;
import cz.cvut.fel.pjv.controllers.SettingsController;
import cz.cvut.fel.pjv.controllers.SimulationController;
import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.UISettings;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class EpidemicSimulatorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // settings
        SimulationSettings simulationSettings = new SimulationSettings();
        UISettings uiSettings = new UISettings(400, 400, 10, 20);
//
//        //adjust settings window
//        SettingsController settingsController = new SettingsController(simulationSettings, uiSettings);
//        settingsController.initializeSettingsController(stage);

//        starting simulation window
        SimulationModel simulationModel = new SimulationModel(simulationSettings, uiSettings);
        SimulationController simulationController = new SimulationController(simulationModel, uiSettings);
        simulationController.runSimulation(stage);


        //
//        Label secondLabel = new Label("I'm a Label on new Window");
//
//        Pane root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
//        root.getChildren().add(secondLabel);
//
//        Scene secondScene = new Scene(root, 230, 100);
//
//        // New window (Stage)
//        Stage newWindow = new Stage();
//        newWindow.setTitle("Second Stage");
//        newWindow.setScene(secondScene);
//
//        // Set position of second window, related to primary window.
//        newWindow.setX(stage.getX() + 200);
//        newWindow.setY(stage.getY() + 100);
//
//        newWindow.show();
    }

    public static void main(String[] args) {
        launch();
    }

}