package Client;

import Interface.ManagerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        try {


            int portNum = 12346;
            String aggregatorURL = "rmi://localhost:" + portNum;
            String aggregator1ObjectLabel = "/Aggregator1";
            ManagerInterface manger1 = (ManagerInterface) Naming.lookup(aggregatorURL + aggregator1ObjectLabel);
            while (true) {

                System.out.println(" Average Temperature from aggregator1 = " + manger1.getAverageTemperatureOfAllSensors());
                Thread.sleep(5000);
            }

        } catch (NotBoundException ex) {
            System.out.println(ex.getMessage());
            //  Logger.getLogger(RMITaskClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(RMITaskClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(RMITaskClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            //  Logger.getLogger(RMITaskClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
