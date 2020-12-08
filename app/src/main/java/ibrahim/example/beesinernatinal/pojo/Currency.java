package ibrahim.example.beesinernatinal.pojo;

public class Currency {
    private String name;
    private String country;
    private String sign;
    private double rate;
    private int flag;

    public Currency(String name, String country, double rate) {
        this.name = name;
        this.country = country;
        this.rate = rate;
    }

    public Currency(String name, String country, String sign, double rate) {
        this.name = name;
        this.country = country;
        this.sign = sign;
        this.rate = rate;
    }

    public Currency(String name, String country, String sign, double rate, int flag) {
        this.name = name;
        this.country = country;
        this.sign = sign;
        this.rate = rate;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getSign() {
        return sign;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return name;
    }
}
