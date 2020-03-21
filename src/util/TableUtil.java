package util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Weather;

public class TableUtil {

    public static TableColumn createColumn(String columnName, String property, int width) {
        TableColumn<Weather, String> column = new TableColumn<>(columnName);
        column.setMinWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }

}
