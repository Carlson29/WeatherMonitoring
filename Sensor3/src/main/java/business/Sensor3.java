package business;

import Interface.SensorInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sensor3 extends UnicastRemoteObject implements SensorInterface {
    private List<Weather> weatherList;
    public Sensor3() throws RemoteException {
        weatherList = new ArrayList<>();
    }
    public void addWeather(Weather weather) throws RemoteException {
        weatherList.add(weather);
        System.out.println(weather.toString());
    }

    public List<Weather> getWeatherList() throws RemoteException  {
        return new ArrayList<>(weatherList);
    }

    public Weather getWeather() throws RemoteException  {
        Scanner sc = new Scanner(System.in);
        double maxTemp = 20;
        double minTemp = -1;
        double temp = minTemp + Math.random() * (maxTemp - minTemp);
        double maxPressure = 1085;
        double minPressure = 870;
        double pressure = minPressure + Math.random() * (maxPressure - minPressure);
        Weather weather = new Weather(temp, pressure);
        return weather;
    }

}
