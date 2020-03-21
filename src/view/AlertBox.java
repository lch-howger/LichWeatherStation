package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {

    /**
     * display alert box
     *
     * @param message
     */
    public void display(String message) {
        //create stage
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Alert");
        stage.setMinWidth(300);

        //create vbox layout
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));

        //create label
        Label label = new Label();
        label.setText(message);

        //create button
        Button button = new Button("ok");
        button.setOnAction(actionEvent -> stage.close());

        vBox.getChildren().addAll(label, button);
        vBox.setAlignment(Pos.CENTER);

        //create scene
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.showAndWait();

    }
}
