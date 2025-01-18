package Classes;


public class Customer
{
    private String queueNumber;

    private String customerName;

    private String parcelID;

    public Customer(String customerName, String queueNumber, String parcelID)
    {
        this.customerName = customerName;
        this.queueNumber = queueNumber;
        this.parcelID = parcelID;
    }
    
    public String getCustomerName()
    {
        return this.customerName;
    }

    public String getQueueNumber()
    {
        return this.queueNumber;
    }

    public String getParcelID()
    {
        return this.parcelID;
    }

    public String toString()
    {
        return "Queue Number: " + this.queueNumber +
        ", Name: " + this.customerName + 
        ", parcelID: " + this.parcelID;
    }
}
