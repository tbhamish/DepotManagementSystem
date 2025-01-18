package GUI;

import Collections.CustomerQueue;
import Collections.ParcelMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import Classes.Customer;
import Classes.Invoice;
import Classes.Parcel;
import Managment.Log;
import java.rmi.UnexpectedException;

import java.util.ArrayList;

public class DepotController 
{
    private CustomerQueue queue;
    private ParcelMap parcelMap;
    private DepotGUI view;

    private Parcel target;

    private Log log;

    public DepotController(CustomerQueue queue, ParcelMap parcelMap, Log log, DepotGUI view) 
    {
        this.queue = queue;
        this.parcelMap = parcelMap;
        this.log = log;
        this.view = view;
        this.target = null;
        initController();
    }

    private void initController() 
    {
        view.getFrame().setTitle("Depot Management");

        view.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        view.getParcelFilter().addActionListener(e -> updateParcelsArea());

        JButton findParcelButton = view.getFindParcelButton();
        findParcelButton.addActionListener(e -> {
            try {
                findParcel(this.view.getParcelIdField().getText());
            } catch (UnexpectedException ex) {
            }
        });

        JButton filterParcel = view.getFilterParcelButton();
        filterParcel.addActionListener(e -> locate());
        
        JButton collectParcel = view.getCollectParcelButton();
        collectParcel.addActionListener(e -> collectParcel());
        
        JButton calculateButton = view.getCalculateButton();
        calculateButton.addActionListener(e -> calculateFee());

        JButton clearButton = view.getClearButton();
        clearButton.addActionListener(e -> reset());

        JMenuItem addCustomerItem = view.getAddCustomerItem();
        addCustomerItem.addActionListener(e -> addCustomer());

        JMenuItem addParcelItem = view.getAddParcelItem();
        addParcelItem.addActionListener(e -> addParcel());
        
        JMenuItem closeItem = view.getCloseItem();
        closeItem.addActionListener(e -> close());

        setupParcelArea();
        setupCustomersArea();
    }

    private void updateParcelsArea() 
    {
        ArrayList<Parcel> parcels = new ArrayList<>();

        String selectedFilter = (String) view.getParcelFilter().getSelectedItem();
        view.clearParcelTable();

        switch (selectedFilter) 
        {
            case "Uncollected Parcels":
                for (Parcel parcel : parcelMap.getParcelDetailsOf(false)) 
                {
                    parcels.add(parcel);
                }
                break;
                
            case "Collected Parcels":
                for (Parcel parcel : parcelMap.getParcelDetailsOf(true)) 
                {
                    parcels.add(parcel);
                }
                break;
            default:
                parcels = parcelMap.getParcelDetailsOf(true);
                parcels.addAll(parcelMap.getParcelDetailsOf(false));
                break;
        }
        
        for (Parcel entry : parcels) 
        {
            view.addParcelToTable(entry);   
        }
    }
    
    private void setupCustomersArea() 
    {
        ArrayList<Customer> customers = queue.getAllCustomer();

        for (Customer entry : customers) 
        {
            view.addCustomerToTable(entry);   
        }
    }

    private void setupParcelArea() 
    {
        ArrayList<Parcel> parcels = parcelMap.getParcelDetailsOf(true);
        parcels.addAll(parcelMap.getParcelDetailsOf(false));

        for (Parcel entry : parcels) 
        {
            view.addParcelToTable(entry);   
        }
    }

    private void reset()
    {
        setupCustomersArea();
        setupParcelArea();
    }

    private void findParcel(String parcelID) throws UnexpectedException 
    {
        target = parcelMap.getParcel(parcelID);

        if(target != null)
        {
            view.getTarget().setText("Selected Parcel ID: " + parcelID);
        }
        else
        {
            throw new UnexpectedException("No Parcel With that ID");
        }
    }

    private void locate() 
    {
        if(target != null)
        {
            Customer tempCus = queue.findCustomer(target.getParcelID());
            
            view.clearCusTable();
            view.clearParcelTable();

            view.addCustomerToTable(tempCus);
            view.addParcelToTable(target);
        }
        else
        {
            view.getTarget().setText("Failed");
        }
    }

    private void collectParcel() 
    {
        if(target != null)
        {
            Customer tempCus = queue.findCustomer(target.getParcelID());
            parcelMap.collectParcel(target.getParcelID());

            Invoice temp = new Invoice(tempCus, target, parcelMap.calculateCost(target.getParcelID()), parcelMap.discount(target.getParcelID(), parcelMap.calculateCost(target.getParcelID())));
            log.addEvent("Package Sold: " + temp.toString());
            queue.removeCustomer(target.getParcelID());

            view.removeParcelFromTable(target);
            view.removeCustomerFromTable(tempCus);
        
        }
        else
        {
            view.getTarget().setText("Failed");
        }
        target = null;
        
    }

    private void calculateFee() {
        if(target != null)
        {
            double temp = parcelMap.calculateCost(target.getParcelID());
            view.getTarget().setText("Cost: " + temp);
        }
        else
        {
            view.getTarget().setText("Failed");
        }
    }

    private void addCustomer() 
    {
        String[] added = view.customerPopUp();
        queue.addToQueue(added[0],added[1]);
        log.addEvent("Customer Added: " + queue.findCustomer(added[1]).toString());
        setupCustomersArea();
    }

    private void addParcel()
    {
        Parcel added = view.parcelPopUp();
        parcelMap.addParcel(added);
        log.addEvent("Parcel Added" + added.toString());
        setupParcelArea();
    }

    private void close() 
    {
        log.writeLogToFile("data/EndDay.txt");
    }
}

