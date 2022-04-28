package cz.cvut.fel.pjv.controllers;

import cz.cvut.fel.pjv.SimulationModel;
import cz.cvut.fel.pjv.handlers.SimulationCanvasHandler;
import cz.cvut.fel.pjv.models.PersonThread;
import cz.cvut.fel.pjv.models.UISettings;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SimulationController {
    private final SimulationModel simulationModel;
    private final UISettings uiSettings;

    private final List<PersonThread> personThreadList = new ArrayList<>();


    public SimulationController(SimulationModel simulationModel, UISettings uiSettings) {
        this.simulationModel = simulationModel;
        this.uiSettings = uiSettings;
    }

    public void runSimulation(Stage stage) {
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
            public void handle(long l) {
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
//                simulationModel.update();
//                simulationCanvasHandler.draw(simulationModel.getPeople());


            }
        };
        timer.start();
    }
}
