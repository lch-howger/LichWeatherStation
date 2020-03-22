package sample;

import factory.MenuFactory;
import factory.TableFactory;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Station;
import model.Weather;
import util.*;
import view.TableBox;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main extends Application {

    private List<Station> list;
    private ObservableList<Weather> data2019;
    private Label label;
    private TableView bottomTable;
    private String selectedStation;
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        //initialize data
        initData();

        //initialize layout
        BorderPane layout = initLayout();

        //initialize stage
        primaryStage.setTitle("Weather Data");
        primaryStage.setScene(new Scene(layout, 1000, 600));
        ImageUtil.setIcon(primaryStage);
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

        file.getItems().get(0).setOnAction(actionEvent -> {
            handleReport();
        });

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
        tableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldWeather, newWeather) -> selectItem(newWeather));

        return tableView;
    }

    /**
     * @param weather
     */
    private void selectItem(Weather weather) {
        selectedStation = weather.getStation();
        if (label != null) {
            label.setText("Station: " + selectedStation);
        }
        if (bottomTable != null) {
            Station station = ListUtil.filterByStation(list, selectedStation);
            if (station != null) {
                bottomTable.setItems(station.getList());
            }
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
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER_LEFT);

        //create label
        label = new Label("");
        Button button = new Button("More");
        button.setOnAction(actionEvent -> {
            new TableBox(list, selectedStation).display();
        });
        hBox.getChildren().addAll(label, button);

        //create table view
        bottomTable = initBottomTable();
        bottomTable.setMaxHeight(200);

        vBox.getChildren().addAll(hBox, bottomTable);
        return vBox;
    }

    /**
     * @return
     */
    private TableView initBottomTable() {
        TableView table = TableFactory.createTable();
        return table;
    }

    /**
     *
     */
    private void handleReport() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        File selectedDirectory = directoryChooser.showDialog(stage);
        File file = new File(selectedDirectory.getAbsolutePath() + File.separator + "WeatherDataReport.txt");
        try {
            file.createNewFile();
            FileUtil.writeReport(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
