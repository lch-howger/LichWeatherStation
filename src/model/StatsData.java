package model;

public class StatsData {
    private String HighestTmax;
    private String LowestTmin;
    private String totalAf;
    private String totalRain;
    private String averageAf;
    private String averageRain;
    private String monthYearTmax;
    private String monthYearTmin;

    public StatsData(String highestTmax, String lowestTmin, String totalAf, String totalRain, String averageAf, String averageRain, String monthYearTmax, String monthYearTmin) {
        HighestTmax = highestTmax;
        LowestTmin = lowestTmin;
        this.totalAf = totalAf;
        this.totalRain = totalRain;
        this.averageAf = averageAf;
        this.averageRain = averageRain;
        this.monthYearTmax = monthYearTmax;
        this.monthYearTmin = monthYearTmin;
    }

    public String getHighestTmax() {
        return HighestTmax;
    }

    public void setHighestTmax(String highestTmax) {
        HighestTmax = highestTmax;
    }

    public String getLowestTmin() {
        return LowestTmin;
    }

    public void setLowestTmin(String lowestTmin) {
        LowestTmin = lowestTmin;
    }

    public String getTotalAf() {
        return totalAf;
    }

    public void setTotalAf(String totalAf) {
        this.totalAf = totalAf;
    }

    public String getTotalRain() {
        return totalRain;
    }

    public void setTotalRain(String totalRain) {
        this.totalRain = totalRain;
    }

    public String getAverageAf() {
        return averageAf;
    }

    public void setAverageAf(String averageAf) {
        this.averageAf = averageAf;
    }

    public String getAverageRain() {
        return averageRain;
    }

    public void setAverageRain(String averageRain) {
        this.averageRain = averageRain;
    }

    public String getMonthYearTmax() {
        return monthYearTmax;
    }

    public void setMonthYearTmax(String monthYearTmax) {
        this.monthYearTmax = monthYearTmax;
    }

    public String getMonthYearTmin() {
        return monthYearTmin;
    }

    public void setMonthYearTmin(String monthYearTmin) {
        this.monthYearTmin = monthYearTmin;
    }
}
