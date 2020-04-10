package factory;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Weather;
import util.TableUtil;

public class TableFactory {

    /**
     * create table
     * @return
     */
    public static TableView createTable() {

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
