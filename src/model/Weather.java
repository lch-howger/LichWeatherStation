package model;

public class Weather {
    private String id;
    private String number;
    private String station;
    private String year;
    private String month;
    private String tmax;
    private String tmin;
    private String af;
    private String rain;
    private String notes;

    public Weather(String id, String number, String station, String year, String month, String tmax, String tmin, String af, String rain) {
        this.id = id;
        this.number = number;
        this.station = station;
        this.year = year;
        this.month = month;
        this.tmax = tmax;
        this.tmin = tmin;
        this.af = af;
        this.rain = rain;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", station='" + station + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", tmax='" + tmax + '\'' +
                ", tmin='" + tmin + '\'' +
                ", af='" + af + '\'' +
                ", rain='" + rain + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTmax() {
        return tmax;
    }

    public void setTmax(String tmax) {
        this.tmax = tmax;
    }

    public String getTmin() {
        return tmin;
    }

    public void setTmin(String tmin) {
        this.tmin = tmin;
    }

    public String getAf() {
        return af;
    }

    public void setAf(String af) {
        this.af = af;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
