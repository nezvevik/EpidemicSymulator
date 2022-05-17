package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.controllers.SettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EpidemicSimulatorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // initializing settings controller
        stage.setTitle("Settings");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Settings.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        SettingsController settingsController = (SettingsController) loader.getController();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}