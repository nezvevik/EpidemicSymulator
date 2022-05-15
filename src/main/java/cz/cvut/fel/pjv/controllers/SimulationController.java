package cz.cvut.fel.pjv.controllers;

import cz.cvut.fel.pjv.SimulationModel;
import cz.cvut.fel.pjv.handlers.SimulationCanvasHandler;
import cz.cvut.fel.pjv.models.PersonThread;
import cz.cvut.fel.pjv.models.UISettings;
import cz.cvut.fel.pjv.controllers.ChartController;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SimulationController {
    private final SimulationModel simulationModel;
    private final UISettings uiSettings;

    private final List<PersonThread> personThreadList = new ArrayList<>();

    private long lastUpdate;
    private long time;
    private long counter;


    public SimulationController(SimulationModel simulationModel, UISettings uiSettings) {
        this.simulationModel = simulationModel;
        this.uiSettings = uiSettings;
        lastUpdate = 0;
        time = 0;
    }

    public void runSimulation(Stage stage) throws IOException {
        Stage stage2 = new Stage();
        stage2.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chart.fxml"));
        Parent root2 = loader.load();
        Scene scene2 = new Scene(root2);
        stage2.setScene(scene2);
        ChartController chartController = (ChartController) loader.getController();

        chartController.updateChart();
        chartController.updateChart();



        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas();
        GraphicsContext context = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        canvas.setWidth(uiSettings.getSimulationWidth());
        canvas.setHeight(uiSettings.getSimulationHeight());

        simulationModel.initialize();
        SimulationCanvasHandler simulationCanvasHandler = new SimulationCanvasHandler(context, uiSettings, simulationModel.getSimulationSettings().getInfectionRange());

        stage.setScene(scene);
        stage.show();



        simulationModel.getPeople().forEach(person -> {
            personThreadList.add(new PersonThread(person, simulationModel, simulationCanvasHandler));
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (counter % 40 == 0) {
                    chartController.updateSeries((XYChart.Series<Number, Number>) chartController.getLineChart().getData().get(0), counter, simulationModel.getNumOfHealthy());
                    chartController.updateSeries((XYChart.Series<Number, Number>) chartController.getLineChart().getData().get(1), counter, simulationModel.getNumOfInfected());
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
        };
        timer.start();
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                simulationCanvasHandler.clearCanvas();
//                personThreadList.forEach(personThread -> {
//                    Thread thread = new Thread(personThread);
//                    thread.start();
//                    try {
//                        thread.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                });
//            }
//        }, 0, 15);


    }
}
