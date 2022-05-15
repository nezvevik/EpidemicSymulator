package cz.cvut.fel.pjv.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Initializable{

    public LineChart lineChart;
    public NumberAxis timeAxis;
    public NumberAxis infectedAxis;

    public LineChart getLineChart() {
        return lineChart;
    }

    public void updateChart() {
        lineChart.getData().add(new XYChart.Series<Number, Number>());
    }

    public void updateSeries(XYChart.Series<Number, Number> series, long x, int y) {
        series.getData().add(new XYChart.Data<>(x, y));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
