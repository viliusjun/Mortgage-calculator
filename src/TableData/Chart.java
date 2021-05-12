/*
Chart class for entering all the data into the LineChart
 */

package TableData;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Chart {
    public static LineChart createChart(double[] sum, double[] payment, double[] percent, int number){
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Months");
        yAxis.setLabel("Sum");
        xAxis.setLowerBound(1);
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(number);
        xAxis.setTickUnit(1);

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        XYChart.Series sumChart = new XYChart.Series();
        XYChart.Series paymentChart = new XYChart.Series();
        XYChart.Series percentChart = new XYChart.Series();

        sumChart.setName("Sum");
        paymentChart.setName("Payment");
        percentChart.setName("Percent");

        for (int i = 0; i < number; ++i){
            sumChart.getData().add(new XYChart.Data(i + 1, sum[i]));
            paymentChart.getData().add(new XYChart.Data(i + 1, payment[i]));
            percentChart.getData().add(new XYChart.Data(i + 1, percent[i]));
        }

        lineChart.setPrefHeight(450);
        lineChart.setPrefWidth(560);


        lineChart.getData().addAll(sumChart, paymentChart, percentChart);

        return lineChart;
    }
}
