package com.songjin.toeatlist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EatItemAdapter extends ArrayAdapter<EatItem>
{
	Context mContext;
	List<EatItem> mEatItems;
	
	public EatItemAdapter(Context context, int textViewResourceId, List<EatItem> objects)
	{
		super(context, textViewResourceId, objects);

		// Put data
		mContext = context;
		mEatItems = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// Set main view
		View view;
		if (convertView == null)
		{
			// Get inflater
			LayoutInflater inflater;
			inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			// Get view from inflater
			view = inflater.inflate(R.layout.item_eatlist, parent, false);
		}
		else
		{
			// Else, get view from convertView
			view = convertView;
		}
		
		// Get data of selected item
		EatItem eatItem;
		eatItem = mEatItems.get(position);
		
		// Fill view data
		ImageView imagePreview;
		imagePreview = (ImageView)view.findViewById(R.id.preview_image);
		if (0 < eatItem.getBitmapCount())
		{
			imagePreview.setImageBitmap(eatItem.getBitmapitem(0).getSubBitmap(0));
		}
		
		TextView textStorename;
		textStorename = (TextView)view.findViewById(R.id.storename_text);
		textStorename.setText(eatItem.getStorename());
		
		TextView textMenuname;
		textMenuname = (TextView)view.findViewById(R.id.menuname_text);
		if (0 < eatItem.getMenunameCount())
		{
			textMenuname.setText(eatItem.getMenuname(0));
		}
		
		TextView textLocationinfo;
		textLocationinfo = (TextView)view.findViewById(R.id.locationinfo_text);
		textLocationinfo.setText(eatItem.getLocation().getLocationname());
		
		return view;
	}
}
