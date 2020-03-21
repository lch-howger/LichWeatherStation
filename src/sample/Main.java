package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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

        return borderPane;
    }

    /**
     * @return
     */
    private void initData() {
        list = FileUtil.initData();
        data2019 = ListUtil.getData2019(list);
    }

    /**
     * initialize menu bar
     *
     * @return
     */
    private MenuBar initMenu() {
        //create menus
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");

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
        TableColumn id = TableUtil.createColumn("Id", "id", 100);
        TableColumn station = TableUtil.createColumn("Station", "station", 200);
        TableColumn tmax = TableUtil.createColumn("Highest monthly maximum temperature", "tmax", 100);
        TableColumn tmin = TableUtil.createColumn("Lowest monthly maximum temperature", "tmin", 100);
        TableColumn frost = TableUtil.createColumn("Total air frost days", "af", 100);
        TableColumn rain = TableUtil.createColumn("Total rainfall", "rain", 100);

        //table
        TableView<Weather> tableView = new TableView<>();
        tableView.setItems(data2019);
        tableView.getColumns().addAll(year, id, station, tmax, tmin, frost, rain);

        return tableView;
    }

}
