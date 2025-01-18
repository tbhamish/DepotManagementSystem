package Collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import Classes.Customer;
import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;


public final class CustomerQueue implements CustomerQueueInterface
{
    private LinkedList<Customer> queue;

    private int queue_number;

    private static CustomerQueue instance;


    private CustomerQueue(String customerFile)
    {
        queue_number = 0;
        queue = new LinkedList<Customer>();

        File file = new File(customerFile);
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
                String[] parts = line.split(",");
                
                if (parts.length == 2) 
                {
                    try 
                    {
                        String customerName = parts[0].trim(); 
                        String parcelName = parts[1].trim(); 
                        
                        
                        //System.out.println("String: " + customerName + ", " + parcelName);

                        addToQueue(customerName, parcelName);
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.out.println("Error parsing numbers in line: " + line);
                    }
                } 
                else 
                {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static CustomerQueue getInstance(String customerFile) 
    {
        if (instance == null) 
        {
            instance = new CustomerQueue(customerFile);
        }
        return instance;
    }

    @Override
    public String nextQueueNumber()
    {
        String tempID = "PARCEL";
        queue_number += 1;

        if(queue_number < 10)
        {
            tempID += "00";
        }
        else if(queue_number < 100)
        {
            tempID += "0";
        }
 
        
        return tempID + String.valueOf(queue_number);
    } 

    @Override
    public void addToQueue(String customer_name, String parcelID)
    {
        queue.add(new Customer(customer_name, nextQueueNumber(), parcelID));
    }

    @Override
    public Customer findCustomer(String parcelID)
    {
        for(Customer cus : queue)
        {
            if(cus.getParcelID().equals(parcelID))
            {
                return cus;
            }
        }
        return null;
    }

    @Override
    public String removeCustomer(String parcelID)
    {
        Customer target = findCustomer(parcelID);

        if(target != null)
        {
            queue.remove(target);
            return target.toString() + " Removed from queue";
        }

        return "No Customer With That Parcel ID";
        
    }

    @Override
    public ArrayList<String> getAllCustomerDetials()
    {
        ArrayList<String> temp = new ArrayList<>();
        
        for (Customer cus : queue)
        {
            temp.add(cus.toString());
            
        }

        return temp;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() 
    {
        ArrayList<Customer> temp = new ArrayList<>();
        
        for (Customer cus : queue)
        {
            temp.add(cus);
            
        }

        return temp;
    }
    
}
