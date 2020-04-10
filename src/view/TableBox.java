package view;

import chart.*;
import factory.TableFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Station;
import model.StatsData;
import util.ListUtil;
import util.StatsUtil;

import java.util.List;

public class TableBox {

    private Station station;

    /**
     * constructor
     *
     * @param station
     */
    public TableBox(Station station) {
        this.station = station;
    }

    /**
     * display the box
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
                    //"Total Annual Af: " + data.getTotalAf() + "\n" +
                    //"Total Annual Rain: " + data.getTotalRain() + "\n" +
                    "Average Annual Af: " + data.getAverageAf() + "\n" +
                    "Average Annual Rain: " + data.getAverageRain());
        }

        //create button
        Button b1 = new Button("View Tmax and Tmin Chart");
        b1.setOnAction(actionEvent -> {
            new ChartBox(station.getList(), "t").display();
        });
        Button b2 = new Button("View Af Chart");
        b2.setOnAction(actionEvent -> {
            new ChartBox(station.getList(), "af").display();
        });
        Button b3 = new Button("View Rain Chart");
        b3.setOnAction(actionEvent -> {
            new ChartBox(station.getList(), "rain").display();
        });

        //create hbox
        HBox hBox = new HBox();
        hBox.getChildren().addAll(label, b1, b2, b3);
        hBox.setSpacing(40);

        //create table
        TableView tableView = TableFactory.createTable();
        tableView.setItems(station.getList());

        //add label and table
        vBox.getChildren().addAll(hBox, tableView);
        vBox.setAlignment(Pos.CENTER_LEFT);

        //create stage
        Stage stage = new BaseStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Station: " + station.getName());
        stage.setScene(new Scene(vBox, 950, 570));
        stage.showAndWait();

    }
}
