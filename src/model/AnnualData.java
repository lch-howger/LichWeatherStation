package model;

public class AnnualData {

    private String averageAf;
    private String averageRain;

    public AnnualData(String averageAf, String averageRain) {
        this.averageAf = averageAf;
        this.averageRain = averageRain;
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
}
