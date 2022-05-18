package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.models.SimulationModel;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartHandler {
    public void setLineSeries(LineChart lineChart, XYChart.Series healthySeries, XYChart.Series infectedSeries) {
        healthySeries = new XYChart.Series<>();
        lineChart.getData().add(healthySeries);
        infectedSeries = new XYChart.Series<>();
        lineChart.getData().add(infectedSeries);
    }

    public void setBarSeries(BarChart barChart, XYChart.Series statSeries) {
        statSeries = new XYChart.Series<>();
        barChart.getData().add(statSeries);
    }

//    public void updateSeries(XYChart.Series<Number, Number> series, long x, int y) {
//        series.getData().add(new XYChart.Data<>(x, y));
//    }

    public void updateBarSeries(long counter, XYChart.Series statSeries, SimulationModel simulationModel) {
        statSeries.getData().add(new XYChart.Data<>("Healthy", simulationModel.getNumOfHealthy()));
        statSeries.getData().add(new XYChart.Data<>("Infected", simulationModel.getNumOfInfected()));
        statSeries.getData().add(new XYChart.Data<>("Post Illness", (simulationModel.getNumOfPeople() - simulationModel.getNumOfHealthy()) - simulationModel.getNumOfInfected()));
    }


}

