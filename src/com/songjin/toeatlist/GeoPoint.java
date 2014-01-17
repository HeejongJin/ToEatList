package com.songjin.toeatlist;

import android.os.Parcel;
import android.os.Parcelable;

public class GeoPoint implements Parcelable
{
	/* Constants */
	private static double E6 = 1000000.0;

	/* Fields */
	private int mLongitudeE6;
	private int mLatitudeE6;
	
	/* Constructors */
	public GeoPoint()
	{
		mLongitudeE6 = 0;
		mLatitudeE6 = 0;
	}
	
	public GeoPoint(int longitudeE6, int latitudeE6)
	{
		set(longitudeE6, latitudeE6);
	}
	
	public GeoPoint(double longitude, double latitude)
	{
		set(longitude, latitude);
	}
	
	/* Methods */
	public double getLongitude()
	{
		return toLongitude(mLongitudeE6);
	}
	
	public double getLatitude()
	{
		return toLatitude(mLatitudeE6);
	}
	
	public int getLongitudeE6()
	{
		return mLongitudeE6;
	}
	
	public int getLatitudeE6()
	{
		return mLatitudeE6;
	}
	
	public void set(int longitudeE6, int latitudeE6)
	{
		mLongitudeE6 = longitudeE6;
		mLatitudeE6 = latitudeE6;
	}
	
	public void set(double longitude, double latitude)
	{
		mLongitudeE6 = toLongitudeE6(longitude);
		mLatitudeE6 = toLatitudeE6(latitude);
	}
	
	static double toLongitude(int longitudeE6)
	{
		return longitudeE6 / E6;
	}
	
	static double toLatitude(int latitudeE6)
	{
		return latitudeE6 / E6;
	}
	
	static int toLongitudeE6(double longitude)
	{
		return (int)(longitude * E6);
	}
	
	static int toLatitudeE6(double latitude)
	{
		return (int)(latitude * E6);
	}
	
	/*
	static double getDistance(GeoPoint from, GeoPoint to)
	{
		return 0.0;
	}
	*/
	
	/* Implemented methods */
	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(mLongitudeE6);
		dest.writeInt(mLatitudeE6);
	}
}
