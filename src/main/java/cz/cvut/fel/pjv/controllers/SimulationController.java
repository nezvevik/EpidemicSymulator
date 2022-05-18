package cz.cvut.fel.pjv.controllers;

import cz.cvut.fel.pjv.handlers.ChartHandler;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    private XYChart.Series series;
    
    public Button resetButton;
    public Button startButton;
    public Button stopButton;
    public Button stepButton;


    private SimulationModel simulationModel;
    private GraphicsContext context;
    private Movement movement;
    SimulationCanvasHandler simulationCanvasHandler;

    private final List<PersonThread> personThreadList = new ArrayList<>();

    private class Movement extends AnimationTimer {
        private final long frames_per_second = 50L;
        private final long interval = 1_000_000_000L/frames_per_second;
        private long offset = 30;

        private long lastRecord = 0;
        private long counter = 0;

        @Override
        public void handle(long now) {
            if (now - lastRecord > interval) {
                if (counter % offset == 0) {
                    series.getData().add(new XYChart.Data<>(counter, simulationModel.getNumOfHealthy()));
                }
                counter++;
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
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = simulationCanvas.getGraphicsContext2D();
    }

    public void runSimulation(Stage stage, SimulationSettings simulationSettings) throws IOException {
        UISettings uiSettings = new UISettings(simulationCanvas.getWidth(), simulationCanvas.getHeight(), 10, 20);

        simulationModel = new SimulationModel(simulationSettings, uiSettings);
        simulationModel.initSimulationModel();

        double maskInfectionRange;
        if (simulationSettings.isMask()) {
            maskInfectionRange = simulationSettings.getInfectionRange() * (1 - simulationSettings.getMaskEfficiency());
        } else {
            maskInfectionRange = simulationSettings.getInfectionRange();
        }

        simulationCanvasHandler = new SimulationCanvasHandler(context, uiSettings, simulationSettings.getInfectionRange(), maskInfectionRange);

        series = new XYChart.Series<>();
        lineChart.getData().add(series);



        simulationModel.getPeople().forEach(person -> {
            personThreadList.add(new PersonThread(person, simulationModel, simulationCanvasHandler));
        });


        movement = new Movement();


        chartHandler = new ChartHandler();
//        chartHandler.setBarSeries(barChart);
//        chartHandler.setLineSeries(lineChart);
    }

    public void resetButtonPressed () {
        System.out.println("reset");
//        movement.stop();
//        simulationModel.initSimulationModel();

    }

    public void startButtonPressed () {
        System.out.println("start");
        movement.start();
        startButton.setDisable(true);
        stopButton.setDisable(false);
    }

    public void stopButtonPressed () {
        System.out.println("stop");
        movement.stop();
        stopButton.setDisable(true);
        startButton.setDisable(false);
    }

    public void stepButtonPressed () {
        System.out.println("step");
    }


}
