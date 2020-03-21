package util;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ImageUtil {

    public static void setIcon(Stage primaryStage) {
        try {
            primaryStage.getIcons().add(new Image(ImageUtil.class.getResourceAsStream("../image/ic_weather.png")));
        } catch (Exception e) {
            System.out.println("Can't find the picture.");
        }
    }
}
