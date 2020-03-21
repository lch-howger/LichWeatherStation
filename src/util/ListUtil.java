package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Station;
import model.Weather;

import java.util.List;

public class ListUtil {

    /**
     * @param list
     * @param name
     * @return
     */
    public static ObservableList<Weather> getListByStation(List<Station> list, String name) {
        for (Station station : list) {
            if (station.getName().equals(name)) {
                return station.getList();
            }
        }
        return FXCollections.observableArrayList();
    }

    /**
     * @param list
     * @param year
     * @return
     */
    public static ObservableList<Weather> getListByYear(List<Station> list, String year) {
        ObservableList<Weather> data = FXCollections.observableArrayList();
        for (Station station : list) {
            Weather weather = getWeatherDataByYear(station, year);
            data.add(weather);
        }
        return data;
    }

    /**
     * get single weather data by year
     *
     * @param station
     * @return
     */
    public static Weather getWeatherDataByYear(Station station, String year) {
        ObservableList<Weather> list = filterByYear(station.getList(), year);
        double tempTmax = 0;
        double tempTmin = 0;
        int totalAirFrostDays = 0;
        double totalRainfall = 0;
        Weather w = new Weather(
                StringUtil.toString(station.getId()),
                StringUtil.toString(station.getId()),
                station.getName(),
                year,
                "all",
                StringUtil.toString(tempTmax),
                StringUtil.toString(tempTmin),
                StringUtil.toString(totalAirFrostDays),
                StringUtil.toString(totalRainfall)
        );
        if (list.isEmpty()) {
            w.setNotes("Lack of data.");
            return w;
        }

        tempTmax = ParseUtil.parseDouble(list.get(0).getTmax());
        tempTmin = ParseUtil.parseDouble(list.get(0).getTmin());

        for (Weather weather : list) {
            double tmax = ParseUtil.parseDouble(weather.getTmax());
            if (tmax > tempTmax) {
                tempTmax = tmax;
            }
            double tmin = ParseUtil.parseDouble(weather.getTmin());
            if (tmin < tempTmin) {
                tempTmin = tmin;
            }
            totalAirFrostDays = totalAirFrostDays + ParseUtil.parseInt(weather.getAf());
            totalRainfall = totalRainfall + ParseUtil.parseDouble(weather.getRain());
        }

        w.setTmax(StringUtil.toString(tempTmax));
        w.setTmin(StringUtil.toString(tempTmin));
        w.setAf(StringUtil.toString(totalAirFrostDays));
        w.setRain(StringUtil.toString(totalRainfall));

        return w;
    }

    /**
     * filter weather list by year
     *
     * @param list
     * @param year
     * @return
     */
    public static ObservableList<Weather> filterByYear(ObservableList<Weather> list, String year) {
        ObservableList<Weather> data = FXCollections.observableArrayList();
        for (Weather weather : list) {
            if (weather.getYear().equals(year)) {
                data.add(weather);
            }
        }
        return data;
    }

    /**
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }
}
