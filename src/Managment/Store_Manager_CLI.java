package Managment;

import Classes.Parcel;
import Collections.CustomerQueue;
import Collections.ParcelMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Store_Manager_CLI //implements Store_Manager_Interface
{
    private static String customerFile;
    private static String parcelFile;

    private static CustomerQueue customers;
    private static ParcelMap parcels;

    public static void main(String[] args) 
    {
        StoreSetup("data/Custs.csv", "data/Parcels.csv");

        Worker employee1 = new Worker(parcels, customers);

        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            boolean exit = false;
            
            while (!exit) 
            {
                System.out.println("Please choose an action:");
                System.out.println("0. Exit");
                System.out.println("1. Print All Parcels");
                System.out.println("2. Print All Queue");
                System.out.println("3. Add Parcel");
                System.out.println("4. Add Customer");
                System.out.println("5. Get Parcel Details");
                System.out.println("6. Get Customer Details");
                System.out.println("7. Remove Parcel (na)");
                System.out.println("8. Remove Customer (na)");
                System.out.println("9. Collect Parcel");
                System.out.println("10. Get Mass Parcel Details");
                System.out.println("11. Calculate Cost");
                System.out.println("12. Make Invoice");
                System.out.println("13. Update Time");
                
                System.out.print("Enter your choice (0-13): ");
                choice = scanner.nextInt();
                
                String name;
                
                switch (choice)
                {
                    case 0:
                        System.out.println("Exiting...");
                        exit = true;
                        break;
                        
                    case 1:
                        System.out.println("Printing all parcels...");
                        ArrayList<Parcel> ParcelTemp = parcels.getParcelDetailsOf(true);
                        ParcelTemp.addAll(parcels.getParcelDetailsOf(false));
                        
                        for(Parcel p : ParcelTemp)
                        {
                            System.out.println(p.toString());
                        }
                        
                    case 2:
                        System.out.println("Printing all customer queue...");
                        ArrayList<String> StringTemp = customers.getAllCustomerDetials();
                        
                        for(String s : StringTemp)
                        {
                            System.out.println(s);
                        }
                        
                    case 3:
                        System.out.println("Adding a new parcel...");
                        System.out.print("Enter Parcel Name: ");
                        name = scanner.nextLine();
                        employee1.addNewParcel(name, 1, 4.0, 5.0, 6.0, 7.0);
                        
                    case 4:
                        System.out.println("Adding a new Customer...");
                        System.out.print("Enter Customer Name: ");
                        name = scanner.nextLine();
                        employee1.addToQueue(name, name);
                        
                        
                    case 5:
                        System.out.println("Getting parcel details...");
                        
                        System.out.print("Enter Parcel Name: ");
                        name = scanner.nextLine();
                        
                        System.out.print(employee1.getParcelDetails(name));
                        
                    case 6:
                        System.out.println("Getting customer details...");
                        
                        System.out.print("Enter Customer Name: ");
                        name = scanner.nextLine();
                        
                        System.out.print(employee1.getCustomerDetails(name));
                        
                    case 9:
                        System.out.println("Collecting parcel...");
                        
                        System.out.print("Enter Parcel Name: ");
                        name = scanner.nextLine();
                        
                        employee1.collectParcel(name);
                        
                    case 10:
                        System.out.println("Getting mass parcel details...");
                        
                        System.out.print("Enter y/n Name: ");
                        name = scanner.nextLine();
                        
                        System.out.print(parcels.getParcelDetailsOf(name.equals("y")));
                        
                        
                    case 11:
                        System.out.println("Calculating cost...");
                        
                        System.out.print("Enter Parcel Name: ");
                        name = scanner.nextLine();
                        
                        System.out.print(parcels.calculateCost(name));
                    case 12:
                        System.out.println("Making invoice...");
                        System.out.print("Enter Parcel Name: ");
                        name = scanner.nextLine();
                        
                        System.out.println(employee1.createInvoice(name).toString());
                        
                    case 13:
                        System.out.println("Updating time...");
                        closeForNight();
                        System.out.print(parcels.getParcelDetailsOf(false));
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static int StoreSetup(String customerFileName, String parcelFileName)
    {
        customerFile = customerFileName;
        parcelFile = parcelFileName;
        
        customers = CustomerQueue.getInstance(customerFileName);
        parcels = ParcelMap.getInstance(parcelFileName);

        return 0;
    }

    public static void closeForNight()
    {
        parcels.updateParcelDuration();
    }


}
