package Classes;

import java.io.Serializable;

public class Parcel implements Serializable, Comparable<Parcel>
{
	private static final long serialVersionUID = 1L;

	private String parcelID;

	private int daysInDepot;
	private boolean collected;

    private Parcel_Dimensions dimensions;

	public Parcel(String parcelID, int daysStored, double weight, double width, double height, double length)
	{
		this.parcelID = parcelID;
		this.daysInDepot = daysStored;
		this.dimensions = new Parcel_Dimensions(width, height, length, weight);
	}

	public String getParcelID()
	{
		return parcelID;
	}

	public void setParcelID(String parcelID)
	{
		this.parcelID = parcelID;
	}

	public int getDaysInDepot()
	{
		return daysInDepot;
	}

	public void setDaysInDepot(int daysInDepot)
	{
		this.daysInDepot = daysInDepot;
	}

    public void increaseDayCount()
	{
		this.daysInDepot++;
	}

    public double calculateParcel()
    {
        return (this.dimensions.getCostMulitplier());
    }

    public boolean getCollected()
    {
        return this.collected;
    }

	public double getWeight()
	{
		return dimensions.getWeight();
	}

	public void setWeight(double weight)
	{
		this.dimensions.setWeight(weight);
	}

	public double getHeight()
    {
        return this.dimensions.getHeight();
    }

	public void setHeight(double height)
    {
        this.dimensions.setHeight(height);
    }

    public double getWidth()
    {
        return this.dimensions.getWidth();
    }

	public void  setWidth(double width)
    {
        this.dimensions.setWidth(width);
    }

    public double getLength()
    {
        return this.dimensions.getLength();
    }

	public void setLength(double length)
    {
        this.dimensions.setLength(length);
    }

	public String collect()
	{
        if(!this.collected)
        {
            this.collected = true;
            return "Collected";
        }
        return "Already Collected";
    }

	public String getDimensions()
	{
		return dimensions.toString();
	}


	@Override
	public int hashCode()
	{
		return parcelID.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean equals = false;

		if (obj instanceof Parcel)
		{
			Parcel that = (Parcel) obj;
			equals = parcelID.equals(that.parcelID);
		}

		return equals;
	}

	@Override
	public int compareTo(Parcel that)
	{
		return -Integer.compare(daysInDepot, that.daysInDepot);
	}

	@Override
	public String toString() 
	{
    	return "Parcel ID : " + parcelID + 
           ", Days In Depot:" + daysInDepot + 
           ", Collected:" + collected + 
            dimensions.toString();
		}

}