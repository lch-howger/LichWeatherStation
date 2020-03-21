package util;

import javafx.collections.ObservableList;
import model.Station;
import model.StatsData;
import model.Weather;

public class StatsUtil {

    /**
     * @param list
     * @return
     */
    public static StatsData getStatsData(ObservableList<Weather> list) {
        if (ListUtil.isEmpty(list)) {
            return null;
        }

        double tempTmax = ParseUtil.parseDouble(list.get(0).getTmax());
        double tempTmin = ParseUtil.parseDouble(list.get(0).getTmin());
        int totalAirFrostDays = 0;
        double totalRainfall = 0;
        String monthYearTmax = "";
        String monthYearTmin = "";

        for (Weather weather : list) {
            double tmax = ParseUtil.parseDouble(weather.getTmax());
            if (tmax > tempTmax) {
                tempTmax = tmax;
                monthYearTmax = weather.getMonth() + "/" + weather.getYear();
            }
            double tmin = ParseUtil.parseDouble(weather.getTmin());
            if (tmin < tempTmin) {
                tempTmin = tmin;
                monthYearTmin = weather.getMonth() + "/" + weather.getYear();
            }
            totalAirFrostDays = totalAirFrostDays + ParseUtil.parseInt(weather.getAf());
            totalRainfall = totalRainfall + ParseUtil.parseDouble(weather.getRain());
        }

        double averageAf = (double) totalAirFrostDays / (double) list.size();
        double averageRain = totalRainfall / (double) list.size();

        StatsData data = new StatsData(
                StringUtil.toString(tempTmax),
                StringUtil.toString(tempTmin),
                StringUtil.toString(totalAirFrostDays),
                StringUtil.toString(totalRainfall),
                StringUtil.toString(averageAf),
                StringUtil.toString(averageRain),
                monthYearTmax,
                monthYearTmin
        );
        return data;
    }
}
