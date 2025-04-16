package business;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Sensor1Server {

    public static void main(String[] args) {
        try {
            Sensor1 sensor1 = new Sensor1();
            int portNum = 12345;


            startRegistry(portNum);
            String registryURL = "rmi://localhost:" + portNum;
            String weatherObjectLabel = "/Sensor1";
            Naming.rebind(registryURL+weatherObjectLabel+"", sensor1);
        } catch (
                RemoteException ex) {
            System.out.println("Error occurred when making remote objects: " + ex.getMessage());
        }
        catch(MalformedURLException ex)
        {
            System.out.println("Error occurred when storing Service Objects at specified URL: " + ex.getMessage());
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
