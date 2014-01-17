package com.songjin.toeatlist;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class EatItem
{
	/* Fields */
	private String mStorename;
	private GeoItem mLocation;
	private ArrayList<String> mMenunames;
	private ArrayList<String> mURLs;
	private ArrayList<BitmapItem> mBitmaps;
	
	/* Constructors */
	public EatItem()
	{
		mStorename = "";
		mLocation = null;
		mMenunames = new ArrayList<String>();
		mURLs = new ArrayList<String>();
		mBitmaps = new ArrayList<BitmapItem>();
	}
	
	public EatItem(String storename, GeoItem location)
	{
		mStorename = storename;
		mLocation = location;
		mMenunames = new ArrayList<String>();
		mURLs = new ArrayList<String>();
		mBitmaps = new ArrayList<BitmapItem>();		
	}
	
	/* Methods */
	void setStorename(String storename)
	{
		mStorename = storename;
	}
	
	void setLocation(GeoItem location)
	{
		mLocation = location;
	}
	
	void addMenuName(String menuname)
	{
		mMenunames.add(menuname);
	}
	
	void addURL(String URL)
	{
		mURLs.add(URL);
	}
	
	String getStorename()
	{
		return mStorename;
	}
	
	GeoItem getLocation()
	{
		return mLocation;
	}
	
	int getMenunameCount()
	{
		return mMenunames.size();
	}
	
	String getMenuname(int position)
	{
		return mMenunames.get(position);
	}
	
	int getURLCount()
	{
		return mURLs.size();
	}
	
	String getURL(int position)
	{
		return mURLs.get(position);
	}
	
	int getBitmapCount()
	{
		return mBitmaps.size();
	}
	
	BitmapItem getBitmapitem(int position)
	{
		return mBitmaps.get(position);
	}
}
