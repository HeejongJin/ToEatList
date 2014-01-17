package com.songjin.toeatlist;

public class GeoItem
{
	/* Fields */
	private String mLocationname;
	private GeoPoint mPoint;
	
	/* Constructors */
	public GeoItem(String locationname, GeoPoint point)
	{
		mLocationname = locationname;
		mPoint = point;
	}
	
	/* Methods */
	public void setLocationName(String locationname)
	{
		mLocationname = locationname;
	}
	
	public void setPoint(GeoPoint point)
	{
		mPoint = point;
	}
	
	public String getLocationname()
	{
		return mLocationname;
	}
	
	public GeoPoint getPoint()
	{
		return mPoint;
	}
}
