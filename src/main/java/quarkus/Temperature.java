package quarkus;

import java.util.Objects;

public class Temperature {


    private String city;
    private Integer min;
    private Integer max;

    public Temperature(String city, Integer min, Integer max) {
        this.city = city;
        this.min = min;
        this.max = max;
    }

    public Temperature() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "city='" + city + '\'' +
                ", min=" + min +
                ", max=" + max +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Objects.equals(city, that.city) && Objects.equals(min, that.min) && Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, min, max);
    }
}
