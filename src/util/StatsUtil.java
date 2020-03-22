package util;

import javafx.collections.ObservableList;
import model.AnnualData;
import model.Station;
import model.StatsData;
import model.Weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        }

        Map<String, ObservableList<Weather>> map = ListUtil.splitListByYear(list);
        AnnualData a = getAnnualData(map);

        StatsData data = new StatsData(
                StringUtil.toString(tempTmax),
                StringUtil.toString(tempTmin),
                null,
                null,
                a.getAverageAf(),
                a.getAverageRain(),
                monthYearTmax,
                monthYearTmin
        );
        return data;
    }

    /**
     * @param map
     * @return
     */
    private static AnnualData getAnnualData(Map<String, ObservableList<Weather>> map) {
        int count = 0;
        int totalAf = 0;
        double totalRain = 0;
        double averageAf = 0;
        double averageRain = 0;
        for (ObservableList<Weather> list : map.values()) {
            if (list.size() == 12) {
                for (Weather weather : list) {
                    totalAf += ParseUtil.parseInt(weather.getAf());
                    totalRain += ParseUtil.parseDouble(weather.getRain());
                }
                count++;
            }
        }

        if (count > 0) {
            averageAf = (double) totalAf / (double) count;
            averageRain = totalRain / (double) count;
        }

        AnnualData a = new AnnualData(
                StringUtil.toString(averageAf),
                StringUtil.toString(averageRain)
        );

        return a;
    }

    public static String getStatsReport(List<Station> list) {
        if (ListUtil.isEmpty(list)) {
            return "";
        }

        String result = "";
        for (Station station : list) {
            StatsData data = getStatsData(station.getList());
            if (data != null) {
                result = result +
                        "Number: " + station.getId() + "\n" +
                        "Station: " + station.getName() + "\n" +
                        "Highest: " + data.getMonthYearTmax() + "   " + data.getHighestTmax() + "\n" +
                        "Lowest: " + data.getMonthYearTmin() + "   " + data.getLowestTmin() + "\n" +
                        "Average annual af: " + data.getAverageAf() + "\n" +
                        "Average annual rainfall: " + data.getAverageRain() + "\n\n";
            } else {
                result = result +
                        "Number: " + station.getId() + "\n" +
                        "Station: " + station.getName() + "\n" +
                        "Notes: Lack of data." + "\n\n";
            }
        }

        return result;
    }

//    private static AnnualData getAnnualData(Map<String, ObservableList<Weather>> map) {
//        String totalAfStr = "";
//        String totalRainStr = "";
//        String AverageAfStr = "";
//        String AverageRainStr = "";
//        for (Map.Entry<String, ObservableList<Weather>> entry : map.entrySet()) {
//            int totalAf = 0;
//            double totalRain = 0;
//            String year = entry.getKey();
//            ObservableList<Weather> list = entry.getValue();
//            for (Weather weather : list) {
//                totalAf += ParseUtil.parseInt(weather.getAf());
//                totalRain += ParseUtil.parseDouble(weather.getRain());
//            }
//
//            double averageAf = (double) totalAf / (double) list.size();
//            double averageRain = totalRain / (double) list.size();
//
//            totalAfStr += StringUtil.toString(totalAf) + "(" + year + ") ";
//            totalRainStr += StringUtil.toString(totalRain) + "(" + year + ") ";
//            AverageAfStr += StringUtil.toString(averageAf) + "(" + year + ") ";
//            AverageRainStr += StringUtil.toString(averageRain) + "(" + year + ") ";
//
//        }
//
//        AnnualData a = new AnnualData(
//                totalAfStr,
//                totalRainStr,
//                AverageAfStr,
//                AverageRainStr
//        );
//
//        return a;
//    }
}
