/*
@author Vilius Junevičius, Vilnius University, software engineering, group 4,
vilius.junevicius@mif.stud.vu.lt

Mortgage calculator calculating the mortgage using the calculator or annuity method.
The data is represented in a table. You can save this data in a .csv file.
 */

package MortgageCalculator;

import CSV.CSVFile;
import TableData.Chart;
import TableData.Data;
import TableData.TableViewHelper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import java.text.DecimalFormat;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Text
        Text calculator = new Text("Mortgage Calculator");

        // Text and Field for amount of loan
        Text amountText = new Text("Amount of loan:  ");
        TextField amountField = new TextField();

        // Text and Field for years and months
        Text yearsText = new Text("Years:");
        TextField yearsField = new TextField();
        Text monthsText = new Text("Months:");
        TextField monthsField = new TextField();

        // Text and ComboBox for the type of mortgage
        Text typeText = new Text("Type:    ");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Linear",
                        "Annuity"
                );
        ComboBox comboBox = new ComboBox(options);

        // Text and Field for percentage
        Text percentageText = new Text("Percentage (%):   ");
        TextField percentageField = new TextField();

        // Text for error messages
        Text errorMessageAmount = new Text("Amount of loan - input decimal or whole numbers. Min(0), MAX(10000000).");
        Text errorMessageYears = new Text("Years - input whole numbers. Min(0), MAX(100).");
        Text errorMessageMonths = new Text("Months - input whole numbers. Min(0), MAX(1200).");
        Text errorMessagePercentage = new Text("Percentage - input decimal or whole numbers. Min(0), MAX(999).");

        // Text, Field and button for interval sum
        Text interval = new Text("Filter for following interval:");
        Text beginningMonth = new Text("From month №:");
        Text endingMonth = new Text("To month №:");
        Text errorMessage = new Text("The numbers of months should include only whole numbers from 1 to last month");
        Text errorMessage2 = new Text("The end month number should be bigger than the beginning month number");
        final Text[] sumText = new Text[1];
        TextField beginningMonthField = new TextField();
        TextField endingMonthField = new TextField();

        //Text for saved data
        Text savedDataText = new Text("The data has been saved to MortgageData.csv");

        // Buttons
        Button buttonList = new Button("List");
        Button buttonFilter = new Button("Filter");
        Button buttonNoFilter = new Button("No Filter");
        Button buttonSaveData = new Button("Save Data");
        Button buttonBack = new Button("Back");
        Button buttonChart = new Button("Chart");
        Button buttonBackFromChart = new Button("Back");

        //Table
        TableView<Data> table = new TableView<>();
        table.getColumns().addAll(TableViewHelper.getMonthNumberColumn(), TableViewHelper.getSumColumn(),
            TableViewHelper.getPaymentColumn(), TableViewHelper.getPercentColumn(), TableViewHelper.getLeftToPayColumn());
        TableView<Data> tableFiltered = new TableView<>();
        tableFiltered.getColumns().addAll(TableViewHelper.getMonthNumberColumn(), TableViewHelper.getSumColumn(),
            TableViewHelper.getPaymentColumn(), TableViewHelper.getPercentColumn(), TableViewHelper.getLeftToPayColumn());

        // Horizontal and Vertical boxes
        HBox hBox = new HBox(calculator);
        HBox hBox1 = new HBox(amountText, amountField);
        HBox hBox2 = new HBox(yearsText, yearsField, monthsText, monthsField);
        HBox hBox3 = new HBox(typeText, comboBox);
        HBox hBox4 = new HBox(percentageText, percentageField);
        VBox vBox = new VBox(hBox, hBox1, hBox2, hBox3, hBox4);
        VBox vBoxErrorMessages = new VBox();
        vBoxErrorMessages.setPadding(new Insets(350, 0, 0, 80));
        HBox hBoxButton = new HBox(buttonList);
        VBox vBox1 = new VBox(hBoxButton);
        HBox hBox6 = new HBox(interval);
        HBox hBox7 = new HBox(beginningMonth, beginningMonthField, endingMonth, endingMonthField);
        VBox vBox4 = new VBox(hBox6, hBox7);
        HBox hboxbuttons = new HBox(buttonFilter, buttonNoFilter);
        VBox vBox5 = new VBox(hboxbuttons);
        VBox vBox6 = new VBox(buttonChart);
        VBox vBox7 = new VBox(buttonBackFromChart);
        vBox5.setPadding(new Insets(455, 0, 0, 20));
        VBox vBoxNotNumbers = new VBox(errorMessage);
        vBoxNotNumbers.setPadding(new Insets(432, 0, 0, 20));
        VBox vBoxBadInterval = new VBox(errorMessage2);
        vBoxBadInterval.setPadding(new Insets(432, 0, 0, 20));
        VBox vBoxSave = new VBox(savedDataText);
        vBoxSave.setPadding(new Insets(448, 0, 0, 320));
        HBox hBoxButtons = new HBox(buttonSaveData, buttonBack);
        hBoxButtons.setSpacing(30);
        VBox vBox3 = new VBox(hBoxButtons);
        VBox vBox2 = new VBox();
        vBox2.getChildren().add(table);


        // set all Styles and measurements for each reference type, Override
        setUp(buttonList, buttonBack, buttonFilter, buttonNoFilter, buttonSaveData, buttonChart, buttonBackFromChart, table, tableFiltered,
                hBox2, vBox, vBox1, vBox4, hBox7, vBox3, vBox2, vBox6, vBox7);
        setUp(errorMessageAmount, errorMessageYears, errorMessageMonths, errorMessagePercentage, interval, beginningMonth,
                endingMonth, errorMessage, errorMessage2, savedDataText, hBox, table, tableFiltered, vBox2);

        // new Panes
        Pane root = new Pane();
        root.setStyle("-fx-background-color: #dfe7df");
        root.getChildren().addAll(vBox, vBox1, vBoxErrorMessages);

        Pane root2 = new Pane();
        root2.setStyle("-fx-background-color: #dfe7df");
        root2.getChildren().addAll(vBox2, vBox3, vBox4, vBox5, vBox6);

        Pane root3 = new Pane();
        root3.setStyle("-fx-background-color: #dfe7df");
        root3.getChildren().addAll(vBox7);

        // new Scenes
        Scene scene = new Scene(root, 600, 510);
        Scene scene2 = new Scene(root2, 600, 510);
        Scene scene3 = new Scene(root3, 600, 510);

        primaryStage.setTitle("Mortgage Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();

        // if button is pressed, do the following
        buttonList.setOnAction(event -> {
            String amountString, yearsString, monthsString, percentageString;
            int years = 0, months = 0;
            double amount = 0, percentage = 0;
            int error = 0;

            // get the String values from the text fields
            amountString = amountField.getText();
            yearsString = yearsField.getText();
            monthsString = monthsField.getText();
            percentageString = percentageField.getText();

            // Validation for entering only whole numbers or decimal numbers with specific bounds
            try {
                amount = Double.parseDouble(amountString);
                if (amount < 0 || amount > 10000000){
                    error = 1;
                    vBoxErrorMessages.getChildren().add(errorMessageAmount);
                }
            } catch (Exception e) {
                vBoxErrorMessages.getChildren().add(errorMessageAmount);
                error = 1;
            }

            try {
                years = Integer.parseInt(yearsString);
                if (years < 0 || years > 100){
                    error = 1;
                    vBoxErrorMessages.getChildren().add(errorMessageYears);
                }
            } catch (Exception e) {
                vBoxErrorMessages.getChildren().add(errorMessageYears);
                error = 1;
            }

            try {
                months = Integer.parseInt(monthsString);
                if (months < 0 || months > 1200){
                    error = 1;
                    vBoxErrorMessages.getChildren().add(errorMessageMonths);
                }
            } catch (Exception e) {
                vBoxErrorMessages.getChildren().add(errorMessageMonths);
                error = 1;
            }

            try {
                percentage = Double.parseDouble(percentageString);
                if (percentage < 0 || percentage > 999){
                    error = 1;
                    vBoxErrorMessages.getChildren().add(errorMessagePercentage);
                }
            } catch (Exception e) {
                vBoxErrorMessages.getChildren().add(errorMessagePercentage);
                error = 1;
            }

            // If no validation error, do the following
            if (error == 0) {
                // We remove the error messages from the scene
                vBoxErrorMessages.getChildren().remove(errorMessageAmount);
                vBoxErrorMessages.getChildren().remove(errorMessageYears);
                vBoxErrorMessages.getChildren().remove(errorMessageMonths);
                vBoxErrorMessages.getChildren().remove(errorMessagePercentage);

                // By the chosen type of mortgage we call functions with specific classes and reference types
                Object chosen = comboBox.getValue();
                if (chosen == "Linear") {
                    Linear linear = new Linear(amount, years, months, percentage);
                    newScene(linear, comboBox, table, buttonSaveData, root2, vBoxSave, primaryStage, scene2, buttonFilter,
                            sumText, beginningMonthField, endingMonthField, vBoxNotNumbers, vBoxBadInterval, vBox2, tableFiltered,
                            buttonNoFilter, buttonChart, root3, scene3, buttonBackFromChart, vBox7);
                }
                else if (chosen == "Annuity"){
                    Annuity annuity = new Annuity(amount, years, months, percentage);
                    newScene(annuity, comboBox, table, buttonSaveData, root2, vBoxSave, primaryStage, scene2, buttonFilter,
                            sumText, beginningMonthField, endingMonthField, vBoxNotNumbers, vBoxBadInterval, vBox2, tableFiltered,
                            buttonNoFilter, buttonChart, root3, scene3, buttonBackFromChart, vBox7);
                }
            }

        });

        // If the Back button is pressed, we remove all entered data in the text field and all error messages,
        //  then we change the scene
        buttonBack.setOnAction(event3 -> {
            table.getItems().clear();
            amountField.clear();
            yearsField.clear();
            monthsField.clear();
            percentageField.clear();
            beginningMonthField.clear();
            endingMonthField.clear();
            root2.getChildren().remove(vBoxNotNumbers);
            root2.getChildren().remove(vBoxBadInterval);
            root2.getChildren().remove(vBoxSave);
            root2.getChildren().remove(sumText[0]);

            primaryStage.setTitle("Mortgage Calculator");
            primaryStage.setScene(scene);
            primaryStage.show();
        });

    }


    // Function that shows the next scene
    void newScene(Calculator calculator, ComboBox comboBox, TableView<Data> table, Button buttonSaveData,
                  Pane root2, VBox vBoxSave, Stage primaryStage, Scene scene2, Button buttonFilter, final Text[] sumText, TextField beginningMonthField,
                  TextField endingMonthField, VBox vBoxNotNumbers, VBox vBoxBadInterval, VBox vBox2, TableView<Data> tableFiltered, Button buttonNoFilter,
                  Button buttonChart, Pane root3, Scene scene3, Button buttonBackFromChart, VBox vBox7){

        double[] leftToPay = new double[calculator.years*12 + calculator.months];
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String[][] allData = new String[5][(calculator.years*12 + calculator.months)*4];
        double[][] allDatadouble = new double[5][(calculator.years*12 + calculator.months)*4];

        double[] monthlyPayment = new double[calculator.years*12 + calculator.months];
        if (comboBox.getValue() == "Linear"){
            monthlyPayment = ((Linear) calculator).linearCalculations();
        }
        else if (comboBox.getValue() == "Annuity"){
            monthlyPayment = ((Annuity) calculator).annuityCalculations();
        }

        // we get all the needed number and string values
        for (int i = 0; i < calculator.years*12 + calculator.months; ++i){
            leftToPay[i] = (calculator.amount) - ((calculator.amount/(calculator.years*12 + calculator.months)) * (i + 1));
            allData[0][i] = Integer.toString(i + 1);
            allData[1][i] = decimalFormat.format(monthlyPayment[i]);
            allData[2][i] = decimalFormat.format(calculator.amount/(calculator.years*12 + calculator.months));
            allData[3][i] = decimalFormat.format(monthlyPayment[i] - (calculator.amount/(calculator.years*12 + calculator.months)));
            allData[4][i] = decimalFormat.format(leftToPay[i]);

            allDatadouble[0][i] = i + 1;
            allDatadouble[1][i] = monthlyPayment[i];
            allDatadouble[2][i] = calculator.amount/(calculator.years*12 + calculator.months);
            allDatadouble[3][i] = monthlyPayment[i] - (calculator.amount/(calculator.years*12 + calculator.months));
            allDatadouble[4][i] = leftToPay[i];
        }

        // Add rows to the TableView
        for (int i = 0; i < calculator.years*12 + calculator.months; ++i){
            leftToPay[i] = (calculator.amount) - ((calculator.amount/(calculator.years*12 + calculator.months)) * (i + 1));

            table.getItems().addAll(TableViewHelper.getDataList(
                    i + 1,
                    decimalFormat.format(monthlyPayment[i]),
                    decimalFormat.format(calculator.amount/(calculator.years*12 + calculator.months)),
                    decimalFormat.format(monthlyPayment[i] - (calculator.amount/(calculator.years*12 + calculator.months))),
                    decimalFormat.format(leftToPay[i]))
            );
        }

        // If the Save Data button is pressed, we call the CSVFile class
        buttonSaveData.setOnAction(event4 -> {
            CSVFile csv = new CSVFile();
            csv.createCSV(allData[0], allData[1], allData[2], allData[3], allData[4], calculator.years*12 + calculator.months);
            root2.getChildren().add(vBoxSave);
        });


        // We start showing the next scene
        primaryStage.setTitle("Data Table");
        primaryStage.setScene(scene2);
        primaryStage.show();

        // If the Chart button is pressed, we load the chart scene
        buttonChart.setOnAction(event5 -> {
            LineChart lineChart = Chart.createChart(allDatadouble[1], allDatadouble[2], allDatadouble[3], calculator.years*12 + calculator.months);
            root3.getChildren().add(lineChart);

            primaryStage.setTitle("Chart");
            primaryStage.setScene(scene3);
            primaryStage.show();

            // we remove the chart from the scene and change the scene when the back button is pressed
            buttonBackFromChart.setOnAction(event6 -> {
                root3.getChildren().remove(lineChart);
                primaryStage.setTitle("Data Table");
                primaryStage.setScene(scene2);
                primaryStage.show();
            });
        });

        // If the filter button is pressed, we filter out the table's data and print out the sum
        buttonFilter.setOnAction(event2 -> {
            root2.getChildren().remove(sumText[0]);
            String beginningMonthString = beginningMonthField.getText();
            String endingMonthString = endingMonthField.getText();
            int beginningMonthValue = 0, endingMonthValue = 0;
            int error2 = 0;

            // Validation for entering the correct beginning and end month numbers
            try {
                beginningMonthValue = Integer.parseInt(beginningMonthString);
                if (beginningMonthValue < 1 || beginningMonthValue > (calculator.years*12 + calculator.months)){
                    error2 = 1;
                    root2.getChildren().remove(vBoxNotNumbers);
                    root2.getChildren().add(vBoxBadInterval);
                }
            } catch (Exception e) {
                root2.getChildren().remove(vBoxBadInterval);
                root2.getChildren().add(vBoxNotNumbers);
                error2 = 1;
            }

            try {
                endingMonthValue = Integer.parseInt(endingMonthString);
                if (endingMonthValue < 1 || endingMonthValue > (calculator.years*12 + calculator.months) || beginningMonthValue > endingMonthValue){
                    error2 = 1;
                    root2.getChildren().remove(vBoxNotNumbers);
                    root2.getChildren().add(vBoxBadInterval);
                }
            } catch (Exception e) {
                root2.getChildren().remove(vBoxBadInterval);
                root2.getChildren().add(vBoxNotNumbers);
                error2 = 1;
            }

            if (error2 == 0){
                root2.getChildren().remove(vBoxNotNumbers);
                root2.getChildren().remove(vBoxBadInterval);
                double sum = 0;

                double[] monthlyPayment2 = new double[calculator.years*12 + calculator.months];

                if (comboBox.getValue() == "Linear"){
                    monthlyPayment2 = ((Linear) calculator).linearCalculations();
                }
                else if (comboBox.getValue() == "Annuity"){
                    monthlyPayment2 = ((Annuity) calculator).annuityCalculations();
                }

                for (int i = beginningMonthValue - 1; i < endingMonthValue; ++i) {
                    sum += monthlyPayment2[i];
                }

                String sumString = decimalFormat.format(sum);
                sumText[0] = new Text("Sum for the following interval = " + sumString);
                sumText[0].setStyle("-fx-font-family: \"Arial\"");
                sumText[0].setX(20);
                sumText[0].setY(450);
                root2.getChildren().add(sumText[0]);

                // Changing tables
                vBox2.getChildren().remove(table);
                vBox2.getChildren().add(tableFiltered);
                tableFiltered.getItems().clear();

                // Adding new data to filtered table
                for (int i = beginningMonthValue - 1; i < endingMonthValue; ++i){
                    tableFiltered.getItems().addAll(TableViewHelper.getDataList(
                            i + 1,
                            decimalFormat.format(monthlyPayment2[i]),
                            decimalFormat.format(calculator.amount/(calculator.years*12 + calculator.months)),
                            decimalFormat.format(monthlyPayment2[i] - (calculator.amount/(calculator.years*12 + calculator.months))),
                            decimalFormat.format(leftToPay[i]))
                    );
                }

                // Changing tables
                buttonNoFilter.setOnAction(event4 -> {
                    tableFiltered.getItems().clear();
                    vBox2.getChildren().remove(tableFiltered);
                    vBox2.getChildren().add(table);
                });
            }

        });
    }

    // Function that sets up the Style of reference types
    void setUp(Text errorMessageAmount, Text errorMessageYears, Text errorMessageMonths, Text errorMessagePercentage, Text interval, Text beginningMonth,
               Text endingMonth, Text errorMessage, Text errorMessage2, Text savedDataText, HBox hBox, TableView<Data> table, TableView<Data> tableFiltered,
               VBox vBox2){

        errorMessageAmount.setStyle("-fx-font-family: \"Arial\"");
        errorMessageYears.setStyle("-fx-font-family: \"Arial\"");
        errorMessageMonths.setStyle("-fx-font-family: \"Arial\"");
        errorMessagePercentage.setStyle("-fx-font-family: \"Arial\"");
        interval.setStyle("-fx-font-family: \"Arial\"");
        beginningMonth.setStyle("-fx-font-family: \"Arial\"");
        endingMonth.setStyle("-fx-font-family: \"Arial\"");
        errorMessage.setStyle("-fx-font-family: \"Arial\"");
        errorMessage2.setStyle("-fx-font-family: \"Arial\"");
        savedDataText.setStyle("-fx-font-family: \"Arial\"");
        vBox2.setStyle("-fx-font-family: \"Arial\"");
        hBox.setStyle("-fx-font-size: 45;");

        errorMessageAmount.setFill(Color.RED);
        errorMessageYears.setFill(Color.RED);
        errorMessageMonths.setFill(Color.RED);
        errorMessagePercentage.setFill(Color.RED);
        errorMessage.setFill(Color.RED);
        errorMessage2.setFill(Color.RED);
        savedDataText.setFill(Color.GREEN);

        table.setPlaceholder(new Label("No visible data exists."));
        tableFiltered.setPlaceholder(new Label("No visible data exists."));
    }

    // Function that sets up the sizes and placings of the reference types
    void setUp(Button buttonList, Button buttonBack, Button buttonFilter, Button buttonNoFilter,
               Button buttonSaveData, Button buttonChart, Button buttonBackFromChart, TableView<Data> table, TableView<Data> tableFiltered, HBox hBox2,
               VBox vBox, VBox vBox1, VBox vBox4, HBox hBox7, VBox vBox3, VBox vBox2, VBox vBox6, VBox vBox7){

        buttonList.setMinSize(100, 30);
        buttonBack.setMinSize(100, 30);
        buttonFilter.setMinSize(100, 30);
        buttonNoFilter.setMinSize(100, 30);
        buttonSaveData.setMinSize(100, 30);
        buttonChart.setMinSize(100, 30);
        buttonBackFromChart.setMinSize(100, 30);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableFiltered.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.setPrefHeight(340);
        tableFiltered.setPrefHeight(340);
        vBox.setPrefWidth(800);
        vBox1.setPrefWidth(800);
        vBox3.setPrefWidth(800);
        vBox2.setPrefWidth(600);
        hBox7.setPrefWidth(360);

        hBox2.setSpacing(20);
        vBox.setSpacing(20);
        vBox1.setSpacing(20);
        vBox3.setSpacing(20);
        vBox4.setSpacing(5);
        hBox7.setSpacing(5);

        vBox1.setPadding(new Insets(465, 0, 0, 450));
        vBox4.setPadding(new Insets(380, 0, 0, 20));
        vBox3.setPadding(new Insets(465, 0, 0, 340));
        vBox2.setPadding(new Insets(20, 20, 0, 20));
        vBox.setPadding(new Insets(65, 100, 0, 80));
        vBox6.setPadding(new Insets(367, 0, 0, 467));
        vBox7.setPadding(new Insets(465, 0, 0, 450));
    }

}