package Server;

import business.Sensor1;
import business.Weather;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Stimulator1 {
    public static void main(String[] args) {
        try {

            Sensor1 sensor1 = new Sensor1();
            int portNum = 12345;


            startRegistry(portNum);
            String registryURL = "rmi://localhost:" + portNum;
            String weatherObjectLabel = "/Sensor1";
            Naming.rebind(registryURL+weatherObjectLabel+"", sensor1);
            while (true) {
               Weather w= sensor1.getWeather();
               sensor1.addWeather(w);
                Thread.sleep(200);
            }
        } catch (
                RemoteException ex) {
            System.out.println("Error occurred when making remote objects: " + ex.getMessage());
        }
        catch(MalformedURLException ex)
        {
            System.out.println("Error occurred when storing Service Objects at specified URL: " + ex.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
