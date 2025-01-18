package uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model;

import Classes.Parcel_Dimensions;
import java.io.Serializable;

public class Parcel implements Serializable, Comparable<Parcel>
{
	private static final long serialVersionUID = 1L;

	private String parcelID;

	private int daysInDepot;
	private boolean collected;

    private Parcel_Dimensions dimensions;

	public Parcel(String parcelID, double weight, double width, double height, double length)
	{
		this.parcelID = parcelID;
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

	public boolean collect()
	{
		if(this.collected)
		{
			return false;
		}

		this.collected = true;
		
		return this.collected;
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
}