package Managment;

import Classes.Customer;
import Classes.Invoice;
import Classes.Parcel;
import Collections.CustomerQueue;
import Collections.ParcelMap;

public class Worker 
{
    ParcelMap parcels;
    CustomerQueue customerQueue;

    public Worker(ParcelMap parcels, CustomerQueue customerQueue)
    {
        this.parcels = parcels;
        this.customerQueue = customerQueue;

    }

    public void addToQueue(String customerName, String parcelID)
    {
        this.customerQueue.addToQueue(customerName, parcelID);
    }

    public void addNewParcel(String parcelID, int parcelDuration, double parcelWeight, double parcelWidth, double parcelHeight, double parcelLength)
    {
       this.parcels.addNewParcel(parcelID, parcelDuration, parcelWeight, parcelWidth, parcelHeight, parcelLength);
    }

    public void collectParcel(String parcelID)
    {
        this.parcels.getParcel(parcelID);

        this.parcels.collectParcel(parcelID);
        this.customerQueue.removeCustomer(parcelID);
    }

    public String getParcelDetails(String parcelID)
    {
        return this.parcels.getParcel(parcelID).toString();
    }

    public String getCustomerDetails(String parcelID)
    {
        return this.customerQueue.findCustomer(parcelID).toString();
    }

    public Invoice createInvoice(String parcelID)
    {

        Customer tempCustomer = this.customerQueue.findCustomer(parcelID);

        Parcel tempParcel = this.parcels.getParcel(parcelID);

        double cost = this.parcels.calculateCost(parcelID);

        double discount;
        discount = this.parcels.discount(parcelID, cost);

        if(tempParcel.getCollected() || (tempCustomer == null))
        {
            return null;
        }

        return new Invoice(tempCustomer, tempParcel, cost, discount);

    }

}
