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
import model.Product;
import model.Weather;
import util.FileUtil;
import util.TableUtil;

import java.util.List;

public class Main extends Application {

    private ObservableList<Weather> list;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //initialize data
        list = initData();

        //initialize layout
        BorderPane layout = initLayout();

        primaryStage.setTitle("Weather Data");
        primaryStage.setScene(new Scene(layout, 800, 600));
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
    private ObservableList<Weather> initData() {
        ObservableList<Weather> list = FileUtil.initData();
        return list;
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
        TableColumn station = TableUtil.createColumn("Station", 100);
        TableColumn tmax = TableUtil.createColumn("Tmax", 100);
        TableColumn tmin = TableUtil.createColumn("Tmin", 100);
        TableColumn frostDays = TableUtil.createColumn("af", 100);
        TableColumn rainfall = TableUtil.createColumn("rain", 100);

        //table
        TableView<Weather> tableView = new TableView<>();
        tableView.setItems(list);
        tableView.getColumns().addAll(station, tmax, tmin, frostDays, rainfall);

        return tableView;
    }

}
