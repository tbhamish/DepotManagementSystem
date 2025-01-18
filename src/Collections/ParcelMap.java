package Collections;

import Classes.Parcel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;



public class ParcelMap implements ParcelMapInterface  
{
    private HashMap<String, Parcel> parcel_map;

    private static ParcelMap instance;


    private ParcelMap(String parcelFileName)
    {
        parcel_map =  new HashMap<>();
            
        File file = new File(parcelFileName);
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
                String[] parts = line.split(",");
                
                if (parts.length == 6) 
                {
                    try 
                    {
                        String parcelName = parts[0].trim(); 
                        int daysStored = Integer.parseInt(parts[1].trim());
                        Double weight = Double.valueOf(parts[2].trim()); 
                        Double width = Double.valueOf(parts[3].trim()); 
                        Double height = Double.valueOf(parts[4].trim()); 
                        Double length = Double.valueOf(parts[5].trim());
                        
                        //System.out.println("String: " + parcelName);
                        //System.out.println("Doubles: " + daysStored + ", " + weight + ", " + width + ", " + height + ", " + length);

                        this.addNewParcel(parcelName, daysStored, weight, width, height, length);
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

    public static ParcelMap getInstance(String parcelFileName) 
    {
        if (instance == null) 
        {
            instance = new ParcelMap(parcelFileName);
        }
        return instance;
    }

    @Override
    public String addNewParcel(String parcelID, int parcelDuration, double parcelWeight, double parcelWidth, double  parcelHeight, double  parcelLength)
    {
        if(parcel_map.containsKey(parcelID))
        {
            return "Parcel: " + parcelID + " already Exists"; 
        }

        parcel_map.put(parcelID, new Parcel(parcelID, parcelDuration, parcelWeight, parcelWidth, parcelHeight, parcelLength));
        return "Added Parcel: " + parcelID;
    }

    @Override
    public String addParcel(Parcel par)
    {
        if(parcel_map.containsKey(par.getParcelID()))
        {
            return "Parcel: " + par.getParcelID() + " already Exists"; 
        }

        parcel_map.put(par.getParcelID(), par);
        return "Added Parcel: " + par.getParcelID();
    }

    @Override
    public Parcel getParcel(String parcelID)
    {
        if(parcel_map.containsKey(parcelID))
        {
            return parcel_map.get(parcelID); 
        }
        return null;
    }

    @Override
    public String collectParcel(String parcelID)
    {
        Parcel target = getParcel(parcelID);
        if(target != null)
        {
            return target.collect();
        }
        return "No Parcel of that ID to collect";
    }

    @Override
    public void updateParcelDuration()
    {
        Set<String> parcelIDSet = parcel_map.keySet();
        
        for(String id : parcelIDSet)
        {
            parcel_map.get(id).increaseDayCount();
        }
    }
    
    @Override
    public ArrayList<Parcel>  getParcelDetailsOf(boolean status)
    {
        Set<String> parcelIDSet = parcel_map.keySet();
        ArrayList<Parcel> parcelReturn = new ArrayList<>();


        for(String id : parcelIDSet)
        {
            if(parcel_map.get(id).getCollected() == status)
            {
                parcelReturn.add(parcel_map.get(id));
            }
        }

        return parcelReturn;
    }

    @Override
    public ArrayList<String>  getAllParcelDetails()
    {
        Set<String> parcelIDSet = parcel_map.keySet();
        ArrayList<String> parcelReturn = new ArrayList<>();


        for(String id : parcelIDSet)
        {
            parcelReturn.add(parcel_map.get(id).toString());
        }

        return parcelReturn;
    }

    @Override
    public double calculateCost(String parcelID)
    {
        Parcel target = getParcel(parcelID);
        double cost = 0;

        if(target != null)
        {
            cost = target.calculateParcel();
            cost = discount(parcelID, cost);
        }
        return cost;

    }

    public double discount(String parcelID, double cost)
    {
        double amount = cost;
        if(parcelID.charAt(parcelID.length() - 1) == '1')
        {
            amount = amount - (amount * 0.15f);
        }

        return amount;
    }
}
