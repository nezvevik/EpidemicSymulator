package cz.cvut.fel.pjv.controllers;

import cz.cvut.fel.pjv.models.SimulationModel;
import cz.cvut.fel.pjv.handlers.SimulationCanvasHandler;
import cz.cvut.fel.pjv.models.PersonThread;
import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.UISettings;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {
    @FXML
    public Canvas canvasBox;
    public BorderPane borderPane;

    private Canvas myCanvas;

    private SimulationSettings simulationSettings;
    private GraphicsContext context;

    private final List<PersonThread> personThreadList = new ArrayList<>();

    private class Movement extends AnimationTimer {
        private final long frames_per_second = 50L;
        private final long interval = 1_000_000_000L/frames_per_second;

        private long lastRecord = 0;

        @Override
        public void handle(long now) {
            if (now - lastRecord > interval) {
//                simulationCanvasHandler.clearCanvas();
                personThreadList.forEach(personThread -> {
                    Thread thread = new Thread(personThread);
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = myCanvas.getGraphicsContext2D();
    }

    public void initSimulationStage(SimulationSettings simulationSettings) throws IOException {
        this.simulationSettings = simulationSettings;
        setSimulationStage();
        runSimulation();

    }

    public void setSimulationStage() throws IOException {
//        Stage stage = new Stage();
//        stage.setTitle("Simulation");
////        Parent root = new FXMLLoader().load();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    public void runSimulation() {
        UISettings uiSettings = new UISettings(400, 400, 10, 20);
        SimulationModel simulationModel = new SimulationModel(simulationSettings, uiSettings);
        System.out.println("canvas: " + canvasBox);
        context = canvasBox.getGraphicsContext2D();
        SimulationCanvasHandler simulationCanvasHandler = new SimulationCanvasHandler(context, uiSettings, simulationSettings.getInfectionRange());

        simulationModel.initSimulationModel();

        simulationModel.getPeople().forEach(person -> {
            personThreadList.add(new PersonThread(person, simulationModel, simulationCanvasHandler));
        });


        Movement movement = new Movement();
        movement.start();



    }
}
