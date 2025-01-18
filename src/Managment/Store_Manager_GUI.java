package Managment;

import Collections.CustomerQueue;
import Collections.ParcelMap;
import GUI.DepotController;
import GUI.DepotGUI;
import Managment.Log;


public class Store_Manager_GUI 
{
    private static String customerFile;
    private static String parcelFile;

    private static CustomerQueue customers;
    private static ParcelMap parcels;
    private static Log log;

    public static void main(String[] args) 
    {
        StoreSetup("data/Custs.csv", "data/Parcels.csv");
        DepotGUI gui = new DepotGUI();
        new DepotController(customers, parcels, log, gui);

    }

    private static int StoreSetup(String customerFileName, String parcelFileName)
    {
        customerFile = customerFileName;
        parcelFile = parcelFileName;
        
        customers = CustomerQueue.getInstance(customerFileName);
        parcels = ParcelMap.getInstance(parcelFileName);
        log = Log.getInstance();

        return 0;
    }

}
