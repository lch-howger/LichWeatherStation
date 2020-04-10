package util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Weather;

public class TableUtil {

    /**
     * create table column
     * @param columnName
     * @param property
     * @param width
     * @return
     */
    public static TableColumn createColumn(String columnName, String property, int width) {
        TableColumn<Weather, String> column = new TableColumn<>(columnName);
        column.setMinWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }

}
