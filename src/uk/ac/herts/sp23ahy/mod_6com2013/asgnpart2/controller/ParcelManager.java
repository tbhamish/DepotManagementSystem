package uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.controller;

import java.util.TreeSet;

import uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model.Parcel;

public class ParcelManager
{
	private final TreeSet<Parcel> parcels = new TreeSet<>();

	public boolean addParcel(Parcel parcel)
	{
		return parcels.add(parcel);
	}

	public void printParcels()
	{
		for (Parcel parcel : parcels)
			System.out.println(parcel.getParcelID());
	}
}