package cz.cvut.fel.pjv.controllers;

import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.UISettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    SimulationSettings simulationSettings;
    UISettings uiSettings;

    public SettingsController(SimulationSettings simulationSettings, UISettings uiSettings) {
        this.simulationSettings = simulationSettings;
        this.uiSettings = uiSettings;
    }

    public void initializeSettingsController(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chart.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas();

        stage.setScene(scene);
        stage.show();
    }

    public void startButton() {
        System.out.println("takzvaná kámost");
    }
}
