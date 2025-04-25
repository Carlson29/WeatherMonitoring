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
            String aggregator2ObjectLabel = "/Aggregator2";
            ManagerInterface manger1 = (ManagerInterface) Naming.lookup(aggregatorURL + aggregator1ObjectLabel);
            ManagerInterface manger2 = (ManagerInterface) Naming.lookup(aggregatorURL + aggregator2ObjectLabel);
            while (true) {

                System.out.println(" Average Temperature from aggregator1 = " + manger1.getAverageTemperatureOfAllSensors());
                System.out.println(" Average Pressure from aggregator1 = " + manger1.getAveragePressureOfAllSensors());

                System.out.println(" Average Temperature from aggregator2 = " + manger2.getAverageTemperatureOfAllSensors());
                System.out.println(" Average Pressure from aggregator2 = " + manger2.getAveragePressureOfAllSensors());

                System.out.println("***************************************");
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
