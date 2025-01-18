package Collections;

import Classes.Customer;
import java.util.ArrayList;

public interface CustomerQueueInterface 
{

    String nextQueueNumber();

    void addToQueue(String customerName, String parcelID);

    Customer findCustomer(String parcelID);

    String removeCustomer(String parcelID);

    ArrayList<String> getAllCustomerDetials();

    ArrayList<Customer> getAllCustomer();


}
