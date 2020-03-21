package sample;

import factory.MenuFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
import util.FileUtil;
import util.ListUtil;
import util.TableUtil;

import java.util.List;

public class Main extends Application {

    private List<Station> list;
    private ObservableList<Weather> data2019;

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

        return borderPane;
    }

    /**
     * @return
     */
    private void initData() {
        list = FileUtil.initData();
        data2019 = ListUtil.getListByYear(list,"2019");
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
        tableView.getColumns().addAll(year, id, station, tmax, tmin, frost, rain,notes);

        return tableView;
    }

    private VBox initVBox() {
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));

        TableView tableView = initTable();
        tableView.setMaxHeight(200);

        vBox.getChildren().addAll(hBox, tableView);
        return vBox;
    }
}
