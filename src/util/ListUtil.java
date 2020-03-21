package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Station;
import model.Weather;

import java.util.List;

public class ListUtil {

    public static ObservableList<Weather> getData2019(List<Station> list) {
        ObservableList<Weather> data = FXCollections.observableArrayList();
        for (Station station : list) {
            Weather weather = getData2019(station);
            data.add(weather);
        }
        return data;
    }

    public static Weather getData2019(Station station) {
        ObservableList<Weather> list = station.getList();
        double totalTmax = 0;
        double totalTmin = 0;
        int totalAirFrostDays = 0;
        double totalRainfall = 0;
        for (Weather weather : list) {
            if (weather.getYear().equals("2019")) {
                totalTmax = totalTmax + ParseUtil.parseDouble(weather.getTmax());
                totalTmin = totalTmin + ParseUtil.parseDouble(weather.getTmin());
                totalAirFrostDays = totalAirFrostDays + ParseUtil.parseInt(weather.getAf());
                totalRainfall = totalRainfall + ParseUtil.parseDouble(weather.getRain());
            }
        }
        Weather weather= new Weather(
                "" + station.getId(),
                station.getName(),
                "2019",
                "2019",
                StringUtil.toString(totalTmax),
                StringUtil.toString(totalTmin),
                StringUtil.toString(totalAirFrostDays),
                StringUtil.toString(totalRainfall)
        );
        return weather;
    }

    public static boolean isEmpty(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }
}
