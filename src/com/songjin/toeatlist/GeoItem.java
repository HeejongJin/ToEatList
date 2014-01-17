package com.songjin.toeatlist;

import android.os.Parcel;
import android.os.Parcelable;

public class GeoItem implements Parcelable
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

	/* Implemented methods */
	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(mLocationname);
		dest.writeParcelable(mPoint, 0);
	}
}
