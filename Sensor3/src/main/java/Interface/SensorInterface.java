package Interface;

import business.Weather;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SensorInterface  extends Remote {
        public List<Weather> getWeatherList() throws RemoteException;
}
