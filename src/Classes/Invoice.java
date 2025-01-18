package Classes;

public class Invoice
{
    private Customer customer;

    private Parcel parcel;

    private double cost;

    private double discount;

    private boolean payed;


    public Invoice(Customer customer, Parcel parcel, double cost, double discount)
    {
        this.customer = customer;
        this.parcel = parcel;
        this.cost = cost;
        this.discount = discount;

        this.payed = false;
    }

    public double getFinalCost()
    {
        return (this.cost * this.discount);
    }

    public String settleInvoice(double transaction)
    {
        double amountDue = getFinalCost();
        if(payed)
        {
            return "Invoice Already Settled";
        }
        if(transaction == amountDue)
        {
            payed = true;
            return "Payment Settled";
        }
        else if(transaction > amountDue)
        {
            return "Attempted to Overpay; Due: £" + amountDue + ", Amount Sent: £" + transaction;
        }
        else
        {
            return "Insuffiecient Amount; Due: £" + amountDue + ", Amount Sent: £" + transaction; 

        }
    }

    public String toString()
    {
        return "Customer Details: " + this.customer.toString() + 
                "\nParcel Details: " + parcel.toString() + 
                "\nAccount Total: " + this.cost + 
                ", Discount Avaliable: " + this.discount + 
                ", Total Due: " + (getFinalCost()) + 
                ", Invoiced Status: " + (this.payed ? "Payed" : "Pending");
    }
    
}
