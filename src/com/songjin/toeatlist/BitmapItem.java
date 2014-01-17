package com.songjin.toeatlist;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class BitmapItem
{
	/* Fields */
	private Bitmap mBitmap;
	private ArrayList<Bitmap> mSubBitmaps;
	
	/* Constructors */
	public BitmapItem(Bitmap bitmap)
	{
		mBitmap = bitmap;
		
		// Call extractSubBitmaps() for making subBitmap data
	}
	
	/* Methods */
	public int getSubBitmapCount()
	{
		return mSubBitmaps.size();
	}
	
	public Bitmap getSubBitmap(int position)
	{
		return mSubBitmaps.get(position);
	}
	
	/*
	public static int extractSubBitmaps(Bitmap bitmap, ArrayList<Bitmap> subBitmaps)
	{
		return 0;
		// return value is the number of extracted bitmaps(0~)
	}
	*/
}
