package aggregator;

import Interface.SensorInterface;
import business.Weather;
import business.WeatherManager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Aggregator2 {
    public static void main(String[] args) {
        try {
            //SensorInterface clientObject = new RemoteTaskClientImpl();
            WeatherManager wm = new WeatherManager();
            String registryURL = "rmi://localhost:12345";

            String objectLabel1 = "/Sensor1";
            String objectLabel2 = "/Sensor2";
            String objectLabel3 = "/Sensor3";

            SensorInterface sensor1 = (SensorInterface) Naming.lookup(registryURL + objectLabel1);
            SensorInterface sensor2 = (SensorInterface) Naming.lookup(registryURL + objectLabel2);
            SensorInterface sensor3 = (SensorInterface) Naming.lookup(registryURL + objectLabel3);
            /*will do the other sensors when they have been created*/
            while (true) {
                List<Weather> sensor1Weather = sensor1.getWeatherList();
                List<Weather> sensor2Weather = sensor2.getWeatherList();
                List<Weather> sensor3Weather = sensor3.getWeatherList();
                wm.setSimulator1(sensor1Weather);
                wm.setSimulator2(sensor2Weather);
                wm.setSimulator3(sensor3Weather);

                double avg1 = wm.getAverageTemperatureOfAllSensors();
                double avg2 = wm.getAveragePressureOfAllSensors();

                System.out.println("Average of temperature and pressure from 3 sensors:");
                System.out.println("Temperature = " + avg1);
                System.out.println("Pressure = " + avg2);
                System.out.println("");

                try {
                    int portNum = 12346;
                    String aggregatorURL = "rmi://localhost:" + portNum;

                    startRegistry(portNum);

                    String aggregatorObjectLabel = "/Agg2";
                    Naming.rebind(aggregatorURL + aggregatorObjectLabel + "", wm);

                } catch (
                        RemoteException ex) {
                    System.out.println("Error occurred when making remote objects: " + ex.getMessage());
                } catch (MalformedURLException ex) {
                    System.out.println("Error occurred when storing Service Objects at specified URL: " + ex.getMessage());
                }
                Thread.sleep(600);
            }

            //UnicastRemoteObject.unexportObject(clientObject, true);
        } catch (NotBoundException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void startRegistry(int RMIPortNum) throws RemoteException {
        try {
            // Try to get the registry at a specific port number
            // If there is no registry started on that port, an exception will be thrown
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);

            registry.list();
        } catch (RemoteException ex) {
            // No valid registry at that port.
            System.out.println("RMI registry cannot be located at port " + RMIPortNum);

            // Create a registry on the given port number
            LocateRegistry.createRegistry(RMIPortNum);
            System.out.println("RMI registry created at port " + RMIPortNum);
        }
    }
}
