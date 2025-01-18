package Test;

import Classes.Parcel;
import Collections.CustomerQueue;
import Collections.ParcelMap;
import Managment.Store_Manager_CLI;
import Managment.Worker;
import java.util.Scanner;

public class StorageManagerCLITest 
{

    public static void main(String[] args)
    {
        System.out.println("Running Store Setup Test...");

        testAddParcel();

        testAddCustomer();

        testPrintAllParcels();

        testPrintCustomerQueue();

        testCollectParcel();

        testGetParcelDetails();

        testCalculateCost();

        testMakeInvoice();

        testUpdateTime();
    }

    private static void testAddParcel() 
    {
        System.out.println("Running Add Parcel Test...");

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Parcel ID: ");
        
        String parcelID = scanner.nextLine();
        
        Worker employee = new Worker(ParcelMap.getInstance("data/Parcels.csv"), CustomerQueue.getInstance("data/Custs.csv"));
        
        employee.addNewParcel(parcelID, 5, 10.0, 5.0, 8.0, 10.0);
        
        System.out.println("Parcel added successfully!");
    }

    // Test case for adding a customer
    private static void testAddCustomer() {
        System.out.println("Running Add Customer Test...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        Worker employee = new Worker(ParcelMap.getInstance("data/Parcels.csv"), CustomerQueue.getInstance("data/Custs.csv"));
        employee.addToQueue(customerName, customerName);
        System.out.println("Customer added to queue!");
    }

    // Test case for printing all parcels
    private static void testPrintAllParcels() {
        System.out.println("Running Print All Parcels Test...");
        ParcelMap parcels = ParcelMap.getInstance("data/Parcels.csv");
        for (Parcel p : parcels.getParcelDetailsOf(true)) {
            System.out.println(p);
        }
        for (Parcel p : parcels.getParcelDetailsOf(false)) {
            System.out.println(p);
        }
    }

    // Test case for printing all customer queue
    private static void testPrintCustomerQueue() {
        System.out.println("Running Print All Customer Queue Test...");
        CustomerQueue customerQueue = CustomerQueue.getInstance("data/Custs.csv");
        for (String customerDetails : customerQueue.getAllCustomerDetials()) {
            System.out.println(customerDetails);
        }
    }

    // Test case for collecting a parcel
    private static void testCollectParcel() {
        System.out.println("Running Collect Parcel Test...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Parcel ID to collect: ");
        String parcelID = scanner.nextLine();

        Worker employee = new Worker(ParcelMap.getInstance("data/Parcels.csv"), CustomerQueue.getInstance("data/Custs.csv"));
        employee.collectParcel(parcelID);
        System.out.println("Parcel collected!");
    }

    // Test case for getting parcel details
    private static void testGetParcelDetails() {
        System.out.println("Running Get Parcel Details Test...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Parcel ID to get details: ");
        String parcelID = scanner.nextLine();

        Worker employee = new Worker(ParcelMap.getInstance("data/Parcels.csv"), CustomerQueue.getInstance("data/Custs.csv"));
        String parcelDetails = employee.getParcelDetails(parcelID);
        System.out.println("Parcel Details: " + parcelDetails);
    }

    // Test case for calculating cost of parcel
    private static void testCalculateCost() {
        System.out.println("Running Calculate Cost Test...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Parcel ID to calculate cost: ");
        String parcelID = scanner.nextLine();

        ParcelMap parcels = ParcelMap.getInstance("data/Parcels.csv");
        double cost = parcels.calculateCost(parcelID);
        System.out.println("Cost of Parcel: " + cost);
    }

    // Test case for creating an invoice
    private static void testMakeInvoice() {
        System.out.println("Running Make Invoice Test...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Parcel ID to create invoice: ");
        String parcelID = scanner.nextLine();

        Worker employee = new Worker(ParcelMap.getInstance("data/Parcels.csv"), CustomerQueue.getInstance("data/Custs.csv"));
        System.out.println(employee.createInvoice(parcelID));
    }

    // Test case for updating time
    private static void testUpdateTime() {
        System.out.println("Running Update Time Test...");
        Store_Manager_CLI.closeForNight();
        ParcelMap parcels = ParcelMap.getInstance("data/Parcels.csv");
        System.out.println("Parcel duration updated!");
    }
}
