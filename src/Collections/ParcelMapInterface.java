package Collections;

import Classes.Parcel;
import java.util.ArrayList;

public interface ParcelMapInterface  
{
    String addNewParcel(String parcelID, int parcelDuration, double parcelWeight, double parcelWidth, double parcelHeight, double  parcelLength);
    
    String addParcel(Parcel par);

    Parcel getParcel(String parcelID);
    
    String collectParcel(String parcelID);
    
    void updateParcelDuration();
    
    ArrayList<Parcel> getParcelDetailsOf(boolean status);

    ArrayList<String>  getAllParcelDetails();

    
    double calculateCost(String parcelID);

    double discount(String parcelID, double cost);

}

