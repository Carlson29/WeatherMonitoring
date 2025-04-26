package business;

import java.io.Serializable;

public class Weather implements Serializable {
    private static final long serialVersionUID = 1L;
    private double temperature;
    private double pressure;

    public Weather(double temperature, double pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public Weather() {
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temperature=" + temperature + " degree Celsius "+
                ", pressure=" + pressure + " hectopascals"+
                '}';
    }
}


