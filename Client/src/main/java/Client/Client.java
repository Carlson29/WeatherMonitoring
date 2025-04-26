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
            String aggregator1ObjectLabel = "/Agg1";
            String aggregator2ObjectLabel = "/Agg2";
            ManagerInterface manger1 = (ManagerInterface) Naming.lookup(aggregatorURL + aggregator1ObjectLabel);
            ManagerInterface manger2 = (ManagerInterface) Naming.lookup(aggregatorURL + aggregator2ObjectLabel);
            while (true) {

                System.out.println("Aggregator1");
                System.out.println("Temperature average: " + manger1.getAverageTemperatureOfAllSensors());
                System.out.println("Pressure average: " + manger1.getAveragePressureOfAllSensors());
                System.out.println(" ");
                System.out.println("Aggregator2");
                System.out.println("Temperature average: " + manger2.getAverageTemperatureOfAllSensors());
                System.out.println("Pressure average:" + manger2.getAveragePressureOfAllSensors());

                System.out.println("************************");
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
