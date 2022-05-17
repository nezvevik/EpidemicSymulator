package cz.cvut.fel.pjv.controllers;

import cz.cvut.fel.pjv.handlers.SimulationCanvasHandler;
import cz.cvut.fel.pjv.models.SimulationModel;
import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.UISettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class SettingsController implements Initializable {
    @FXML
    public GridPane gridPane1;

    private List<Label> labels = new ArrayList<>();
    private List<ScrollBar> scrollBars = new ArrayList<>();

    public void initSettingsStage(Stage stage) throws IOException {
        // setting up stage and loading scene from FXML file
        stage.setTitle("Settings");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Settings.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // finding labels from third column and putting them in a list
        gridPane1.getChildren().forEach(node -> {
            if (gridPane1.getColumnIndex(node) != null && gridPane1.getColumnIndex(node) == 2) {
                labels.add((Label) node);
            }
        });

        // going through all ScrollBars and linking them to according labels
        gridPane1.getChildren().forEach(node -> {
            if (node instanceof ScrollBar) {
                scrollBars.add((ScrollBar) node);
                labels.get(gridPane1.getRowIndex(node)).setText(String.valueOf((int) ((ScrollBar) node).getValue()));
                ((ScrollBar) node).valueProperty().addListener((observableValue, number, t1) -> {
                    labels.get(gridPane1.getRowIndex(node)).setText(String.valueOf((int) ((ScrollBar) node).getValue()));
                });
            }
        });
    }

    @FXML
    public void runButtonPressed(ActionEvent event) throws IOException {
        // button that starts the simulation
        // default UISettings
        SimulationSettings simulationSettings = new SimulationSettings((int) scrollBars.get(0).getValue(), (int) scrollBars.get(1).getValue(), (int) scrollBars.get(2).getValue(),  scrollBars.get(3).getValue(),  scrollBars.get(4).getValue(), (int) scrollBars.get(5).getValue(), scrollBars.get(6).getValue());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Simulation.fxml"));
        SimulationController simulationController = (SimulationController) loader.getController();
        simulationController.initSimulationStage(simulationSettings);
//        simulationController.runSimulation();
    }
}
