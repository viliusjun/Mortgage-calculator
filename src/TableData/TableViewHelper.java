/*
TableViewHelper class for showing the table
 */

package TableData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


public class TableViewHelper
{
    // Returns an observable list of persons
    public static ObservableList<Data> getDataList(int monthNumber, String sum, String payment, String percent, String leftToPay)
    {
        Data newData = new Data(monthNumber, sum, payment, percent, leftToPay);

        return FXCollections.<Data>observableArrayList(newData);
    }

    // Returns sum TableColumn
    public static TableColumn<Data, Integer> getMonthNumberColumn()
    {
        TableColumn<Data, Integer> monthNumberColumn = new TableColumn<>("Month Number");
        PropertyValueFactory<Data, Integer> monthNumberCellValueFactory = new PropertyValueFactory<>("monthNumber");
        monthNumberColumn.setCellValueFactory(monthNumberCellValueFactory);
        return monthNumberColumn;
    }

    // Returns sum TableColumn
    public static TableColumn<Data, String> getSumColumn()
    {
        TableColumn<Data, String> sumColumn = new TableColumn<>("Sum");
        PropertyValueFactory<Data, String> sumCellValueFactory = new PropertyValueFactory<>("sum");
        sumColumn.setCellValueFactory(sumCellValueFactory);
        return sumColumn;
    }

    // Returns sum TableColumn
    public static TableColumn<Data, String> getPaymentColumn()
    {
        TableColumn<Data, String> paymentColumn = new TableColumn<>("Payment");
        PropertyValueFactory<Data, String> sumCellValueFactory = new PropertyValueFactory<>("payment");
        paymentColumn.setCellValueFactory(sumCellValueFactory);
        return paymentColumn;
    }

    // Returns percent TableColumn
    public static TableColumn<Data, String> getPercentColumn()
    {
        TableColumn<Data, String> percentColumn = new TableColumn<>("Percent");
        PropertyValueFactory<Data, String> percentCellValueFactory = new PropertyValueFactory<>("percent");
        percentColumn.setCellValueFactory(percentCellValueFactory);
        return percentColumn;
    }

    // Returns left to pay TableColumn
    public static TableColumn<Data, String> getLeftToPayColumn()
    {
        TableColumn<Data, String> leftToPayColumn = new TableColumn<>("Left To Pay");
        PropertyValueFactory<Data, String> leftToPayCellValueFactory = new PropertyValueFactory<>("leftToPay");
        leftToPayColumn.setCellValueFactory(leftToPayCellValueFactory);
        return leftToPayColumn;
    }

}