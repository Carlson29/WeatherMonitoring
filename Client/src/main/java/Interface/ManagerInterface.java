package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ManagerInterface extends Remote {
    public double getAverageTemperatureOfAllSensors() throws RemoteException;
    public double getAveragePressureOfAllSensors() throws RemoteException ;
}
