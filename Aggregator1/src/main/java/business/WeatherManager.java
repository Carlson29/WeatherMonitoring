package business;

import Interface.ManagerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class WeatherManager extends UnicastRemoteObject implements ManagerInterface {
    private List<Weather> simulator1;
    private List<Weather> simulator2;
    private List<Weather> simulator3;

    public WeatherManager(List<Weather> simulator1, List<Weather> simulator2, List<Weather> simulator3) throws RemoteException {
        this.simulator1 = simulator1;
        this.simulator2 = simulator2;
        this.simulator3 = simulator3;
    }

    public WeatherManager() throws RemoteException  {
    }

    public List<Weather> getSimulator1() {
        return simulator1;
    }

    public void setSimulator1(List<Weather> simulator1) {
        this.simulator1 = simulator1;
    }

    public List<Weather> getSimulator2() {
        return simulator2;
    }

    public void setSimulator2(List<Weather> simulator2) {
        this.simulator2 = simulator2;
    }

    public List<Weather> getSimulator3() {
        return simulator3;
    }

    public void setSimulator3(List<Weather> simulator3) {
        this.simulator3 = simulator3;
    }

    public double getAverageTemperature(List<Weather> simulator) throws RemoteException  {
        int i = simulator.size() - 1;
        int sum = 0;
        double average = 0;
        int count = 0;
        while (i > 0 && count < 50) {
            sum += simulator.get(i).getTemperature();
            i--;
            count++;
        }

        if (simulator.size() < 50 && simulator.size() > 0) {
            average = sum / simulator.size();
        } else if (simulator.size() > 50 && simulator.size() > 0) {
            average = sum / 50;
        }
        return average;
    }

    public double getAverageTemperatureOfAllSensors() throws RemoteException  {
        double avg1 = getAverageTemperature(simulator1);
        /*double avg2 = getAverageTemperature(simulator2);
        double avg3 = getAverageTemperature(simulator3);*/
        return (avg1) ;
    }

    public double getAveragePressure(List<Weather> simulator){
        int i = simulator.size() - 1;
        int sum = 0;
        double average = 0;
        int count = 0;
        while (i > 0 && count < 100) {
            sum += simulator.get(i).getPressure();
            i--;
            count++;
        }

        if (simulator.size() < 100 && simulator.size() > 0) {
            average = sum / simulator.size();
        } else if (simulator.size() > 100 && simulator.size() > 0) {
            average = sum / 100;
        }
        return average;
    }

    public double getAveragePressureOfAllSensors() throws RemoteException {
        double avg1 = getAveragePressure(simulator1);
        double avg2 = getAveragePressure(simulator2);
        double avg3 = getAveragePressure(simulator3);
        return (avg1 + avg2 + avg3) / 3;
    }


}
