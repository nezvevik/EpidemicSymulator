package cz.cvut.fel.pjv.controllers;

import javafx.fxml.FXML;
import cz.cvut.fel.pjv.models.SimulationSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    public GridPane gridPane;
    public CheckBox distancingCheckBox;
    public CheckBox maskCheckBox;

    private List<Label> labels = new ArrayList<>();
    private List<ScrollBar> scrollBars = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // finding labels from third column and putting them in a list
        gridPane.getChildren().forEach(node -> {
            if (gridPane.getColumnIndex(node) != null && gridPane.getColumnIndex(node) == 2) {
                labels.add((Label) node);
            }
        });

        // going through all ScrollBars and linking them to according labels
        gridPane.getChildren().forEach(node -> {
            if (node instanceof ScrollBar) {
                scrollBars.add((ScrollBar) node);
                labels.get(gridPane.getRowIndex(node)).setText(String.valueOf((int) ((ScrollBar) node).getValue()));
                ((ScrollBar) node).valueProperty().addListener((observableValue, number, t1) -> {
                    labels.get(gridPane.getRowIndex(node)).setText(String.valueOf((int) ((ScrollBar) node).getValue()));
                });
            }
        });
    }

    @FXML
    public void runButtonPressed(ActionEvent event) throws IOException {
        SimulationSettings simulationSettings = new SimulationSettings((int) scrollBars.get(0).getValue(),
                (int) scrollBars.get(1).getValue(),
                (int) scrollBars.get(2).getValue(),
                scrollBars.get(3).getValue(),
                scrollBars.get(4).getValue(),
                (int) scrollBars.get(5).getValue(),
                scrollBars.get(6).getValue(),
                maskCheckBox.isSelected(),
                scrollBars.get(7).getValue(),
                distancingCheckBox.isSelected(),
                scrollBars.get(8).getValue());
        FXMLLoader loaderSimulation = new FXMLLoader(getClass().getResource("/Simulation.fxml"));

        Stage stage = new Stage();
        setSimulationStage(stage, loaderSimulation);

        SimulationController simulationController = (SimulationController) loaderSimulation.getController();
        simulationController.runSimulation(stage, simulationSettings);
    }

    private void setSimulationStage(Stage stage, FXMLLoader loaderSimulation) throws IOException {
        stage.setTitle("Simulation");
        Parent root = loaderSimulation.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
