package view;

import factory.TableFactory;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Station;
import model.StatsData;
import model.Weather;
import util.ListUtil;
import util.StatsUtil;

import java.util.List;

public class TableBox {

    private Station station;

    /**
     * @param list
     * @param name
     */
    public TableBox(List<Station> list, String name) {
        //get weather data list by station
        station = ListUtil.filterByStation(list, name);
    }

    /**
     *
     */
    public void display() {

        //create vbox layout
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(20);

        //create label
        StatsData data = StatsUtil.getStatsData(station.getList());
        Label label = new Label();
        if (data != null) {
            label.setText("Station: " + station.getName() + "\n" +
                    "Highest Tmax: " + data.getMonthYearTmax() + "   " + data.getHighestTmax() + "\n" +
                    "Lowest Tmin: " + data.getMonthYearTmin() + "   " + data.getLowestTmin() + "\n" +
                    "Total Af: " + data.getTotalAf() + "\n" +
                    "Total Rain: " + data.getTotalRain() + "\n" +
                    "Average Af: " + data.getAverageAf() + "\n" +
                    "Average Rain: " + data.getAverageRain());
        }

        //create table
        TableView tableView = TableFactory.createTable();
        tableView.setItems(station.getList());

        //add label and table
        vBox.getChildren().addAll(label, tableView);
        vBox.setAlignment(Pos.CENTER_LEFT);

        //create stage
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Station: " + station.getName());
        stage.setScene(new Scene(vBox,950,570));
        stage.showAndWait();

    }
}
