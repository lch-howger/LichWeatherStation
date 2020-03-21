package sample;

import factory.MenuFactory;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Station;
import model.Weather;
import util.AlertUtil;
import util.FileUtil;
import util.ListUtil;
import util.TableUtil;

import java.util.List;

public class Main extends Application {

    private List<Station> list;
    private ObservableList<Weather> data2019;
    private Label label;
    private TableView tableView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //initialize data
        initData();

        //initialize layout
        BorderPane layout = initLayout();

        //initialize stage
        primaryStage.setTitle("Weather Data");
        primaryStage.setScene(new Scene(layout, 1000, 600));
        primaryStage.show();
    }

    /**
     * initialize border pane layout
     *
     * @return
     */
    private BorderPane initLayout() {
        //create border pane
        BorderPane borderPane = new BorderPane();

        //initialize menu
        MenuBar menuBar = initMenu();
        borderPane.setTop(menuBar);

        //initialize table
        TableView tableView = initTable();
        borderPane.setCenter(tableView);

        //initialize table
        VBox vBox = initVBox();
        borderPane.setBottom(vBox);

        //select item
        tableView.getSelectionModel().select(0);

        return borderPane;
    }

    /**
     * @return
     */
    private void initData() {
        list = FileUtil.initData();
        data2019 = ListUtil.getListByYear(list, "2019");
    }

    /**
     * initialize menu bar
     *
     * @return
     */
    private MenuBar initMenu() {
        //create menus
        Menu file = MenuFactory.createMenu("File");
        Menu edit = MenuFactory.createMenu("Edit");
        Menu help = MenuFactory.createMenu("Help");

        //create menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file, edit, help);

        return menuBar;
    }

    /**
     * initialize table view
     *
     * @return
     */
    private TableView initTable() {

        //create columns
        TableColumn year = TableUtil.createColumn("Year", "year", 100);
        TableColumn id = TableUtil.createColumn("Number", "id", 100);
        TableColumn station = TableUtil.createColumn("Station", "station", 200);
        TableColumn tmax = TableUtil.createColumn("Highest monthly maximum temperature", "tmax", 100);
        TableColumn tmin = TableUtil.createColumn("Lowest monthly maximum temperature", "tmin", 100);
        TableColumn frost = TableUtil.createColumn("Total air frost days", "af", 100);
        TableColumn rain = TableUtil.createColumn("Total rainfall", "rain", 100);
        TableColumn notes = TableUtil.createColumn("Notes", "notes", 100);

        //table
        TableView<Weather> tableView = new TableView<>();
        tableView.setItems(data2019);
        tableView.getColumns().addAll(year, id, station, tmax, tmin, frost, rain, notes);

        //selected item
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Weather>() {
            @Override
            public void changed(ObservableValue<? extends Weather> observableValue, Weather oldWeather, Weather newWeather) {
                selectItem(newWeather);
            }
        });

        return tableView;
    }

    /**
     * @param weather
     */
    private void selectItem(Weather weather) {
        if (label != null) {
            label.setText("Station: " + weather.getStation());
        }
        if (tableView != null) {
            tableView.setItems(ListUtil.getListByStation(list, weather.getStation()));
        }
    }

    /**
     * @return
     */
    private VBox initVBox() {

        //create vbox layout
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));

        //create label
        label = new Label("");
        hBox.getChildren().add(label);

        //create table view
        tableView = initBottomTable();
        tableView.setMaxHeight(200);

        vBox.getChildren().addAll(hBox, tableView);
        return vBox;
    }

    /**
     * @return
     */
    private TableView initBottomTable() {

        //create columns
        TableColumn station = TableUtil.createColumn("Station", "station", 150);
        TableColumn id = TableUtil.createColumn("Number", "number", 100);
        TableColumn year = TableUtil.createColumn("Year", "year", 100);
        TableColumn month = TableUtil.createColumn("Month", "month", 100);
        TableColumn tmax = TableUtil.createColumn("Tmax (maximum temperature in the month)", "tmax", 100);
        TableColumn tmin = TableUtil.createColumn("Tmin (minimum temperature in the month)", "tmin", 100);
        TableColumn frost = TableUtil.createColumn("Af (days of air frost in the month)", "af", 100);
        TableColumn rain = TableUtil.createColumn("Rain (total rainfall in the month)", "rain", 100);

        //table
        TableView<Weather> tableView = new TableView<>();
        tableView.getColumns().addAll(station, id, year, month, tmax, tmin, frost, rain);

        return tableView;
    }
}
