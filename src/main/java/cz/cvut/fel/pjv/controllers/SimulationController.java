package cz.cvut.fel.pjv.controllers;

import cz.cvut.fel.pjv.handlers.ChartHandler;
import cz.cvut.fel.pjv.models.SimulationModel;
import cz.cvut.fel.pjv.handlers.SimulationCanvasHandler;
import cz.cvut.fel.pjv.models.PersonThread;
import cz.cvut.fel.pjv.models.SimulationSettings;
import cz.cvut.fel.pjv.models.UISettings;
import cz.cvut.fel.pjv.models.person.InfectionPhase;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {
    @FXML
    public BorderPane borderPane;
    public Canvas simulationCanvas;
    
    public LineChart lineChart;
    public BarChart barChart;

    private ChartHandler chartHandler;
    private XYChart.Series healthySeries;
    private XYChart.Series infectedSeries;
    private XYChart.Series statSeries;
    
    public Button resetButton;
    public Button startButton;
    public Button stopButton;

    private Stage stage;
    private SimulationSettings simulationSettings;

    private SimulationModel simulationModel;
    private GraphicsContext context;
    private Movement movement;
    private SimulationCanvasHandler simulationCanvasHandler;

    private long FPS;


    private final List<PersonThread> personThreadList = new ArrayList<>();

    private class Movement extends AnimationTimer {
        private final long movementFPS = FPS;
        private final long interval = 1_000_000_000L/movementFPS;
        private long offset = 50;

        private long lastRecord = 0;
        private long counter = 0;
        private boolean updateGraph = true;

        public Movement() {
            lastRecord = 0;
            counter = 0;
            updateGraph = true;
        }

        @Override
        public void handle(long now) {
            if (now - lastRecord > interval) {
                if (updateGraph) {
                    if (simulationModel.getNumOfInfected() == 0) {
                        chartHandler.updateBarSeries(counter, statSeries, simulationModel);
                        updateGraph = false;
                    }
                    if (counter % offset == 0) {
                        healthySeries.getData().add(new XYChart.Data<>(counter, simulationModel.getNumOfHealthy()));
                        infectedSeries.getData().add(new XYChart.Data<>(counter, simulationModel.getNumOfInfected()));
                        chartHandler.updateBarSeries(counter, statSeries, simulationModel);
                    }
                    counter++;
                }
                simulationCanvasHandler.clearCanvas();
                personThreadList.forEach(personThread -> {
                    Thread thread = new Thread(personThread);
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                lastRecord = now;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = simulationCanvas.getGraphicsContext2D();
    }

    public void runSimulation(Stage stage, SimulationSettings simulationSettings) throws IOException {
        this.stage = stage;
        this.simulationSettings = simulationSettings;

        UISettings uiSettings = new UISettings(simulationCanvas.getWidth(), simulationCanvas.getHeight(), 10, 20, 30L);
        FPS = uiSettings.getFPS();

        simulationModel = new SimulationModel(simulationSettings, uiSettings);
        simulationModel.initSimulationModel();

        double maskInfectionRange;
        if (simulationSettings.isMask()) {
            maskInfectionRange = simulationSettings.getInfectionRange() * (1 - simulationSettings.getMaskEfficiency());
        } else {
            maskInfectionRange = simulationSettings.getInfectionRange();
        }

        simulationCanvasHandler = new SimulationCanvasHandler(context, uiSettings, simulationSettings.getInfectionRange(), maskInfectionRange);

        // set up graphs
        chartHandler = new ChartHandler();
        healthySeries = new XYChart.Series<>();
        lineChart.getData().add(healthySeries);
        infectedSeries = new XYChart.Series<>();
        lineChart.getData().add(infectedSeries);
        statSeries = new XYChart.Series<>();
        barChart.getData().add(statSeries);


        personThreadList.clear();
        simulationModel.getPeople().forEach(person -> {
            personThreadList.add(new PersonThread(person, simulationModel, simulationCanvasHandler));
        });


        movement = new Movement();

        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                movement.stop();
            }
        });



    }

    public void resetButtonPressed () throws IOException {
        movement.stop();
        simulationCanvasHandler.clearCanvas();
        runSimulation(stage, simulationSettings);
        startButton.setDisable(false);
        resetButton.setDisable(true);
        stopButton.setDisable(true);
    }

    public void startButtonPressed () {
        simulationModel.getPeople().forEach(person -> {
            if (person.getInfectionPhase() == InfectionPhase.INFECTED) {
                simulationModel.infectionTimer(person);
            }
        });
        movement.start();
        startButton.setDisable(true);
        stopButton.setDisable(false);
        resetButton.setDisable(true);
    }

    public void stopButtonPressed () {
        movement.stop();
        stopButton.setDisable(true);
        startButton.setDisable(false);
        resetButton.setDisable(false);
    }
}
