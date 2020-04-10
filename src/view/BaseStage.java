package view;

import javafx.stage.Stage;
import util.ImageUtil;

public class BaseStage extends Stage {

    public BaseStage() {
        super();
        ImageUtil.setIcon(this);
    }
}
