package chart;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import model.Weather;
import util.ParseUtil;
import view.BaseStage;

public class ChartBox {

    private ObservableList<Weather> list;
    private String tag;

    /**
     * constructor
     * @param list
     * @param tag
     */
    public ChartBox(ObservableList<Weather> list, String tag) {
        this.list = list;
        this.tag = tag;
    }

    /**
     * display the box
     */
    public void display() {
        //create line chart
        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        XYChart<String, Number> chart = new LineChart<>(x, y);

        //create series
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        //add data to series
        if ("t".equals(tag)) {
            for (Weather w : list) {
                series.getData().add(new XYChart.Data(w.getYear() + "-" + w.getMonth(), ParseUtil.parseDouble(w.getTmax())));
                series2.getData().add(new XYChart.Data(w.getYear() + "-" + w.getMonth(), ParseUtil.parseDouble(w.getTmin())));
            }
        } else if ("af".equals(tag)) {
            for (Weather w : list) {
                series.getData().add(new XYChart.Data(w.getYear() + "-" + w.getMonth(), ParseUtil.parseDouble(w.getAf())));
            }
        } else if ("rain".equals(tag)) {
            for (Weather w : list) {
                series.getData().add(new XYChart.Data(w.getYear() + "-" + w.getMonth(), ParseUtil.parseDouble(w.getRain())));
            }
        }

        //add all series
        chart.getData().addAll(series, series2);

        //show stage
        Stage stage = new BaseStage();
        stage.setScene(new Scene(chart, 800, 500));
        stage.showAndWait();
    }
}
