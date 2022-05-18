package cz.cvut.fel.pjv.handlers;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartHandler {
    public void setLineSeries(LineChart chart) {
        chart.getData().add(new XYChart.Series<Number, Number>());
    }

    public void setBarSeries(BarChart chart) {
        chart.getData().add(new XYChart.Series<Number, Number>());
    }

    public void updateSeries(XYChart.Series<Number, Number> series, long x, int y) {
        series.getData().add(new XYChart.Data<>(x, y));
    }
}
