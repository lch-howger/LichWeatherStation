package util;

import javafx.scene.control.Alert;

public class AlertUtil {

    /**
     *
     * @param message
     */
    public static void alert(String message) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Alert");
        dialog.setHeaderText("");
        dialog.setContentText(message);
        dialog.showAndWait();
    }

}
