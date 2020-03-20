package util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.Weather;

public class TableUtil {

    public static TableColumn createColumn(String name, int width) {
        TableColumn<Weather, String> column = new TableColumn<>(name);
        column.setMinWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(name));
        return column;
    }
}
