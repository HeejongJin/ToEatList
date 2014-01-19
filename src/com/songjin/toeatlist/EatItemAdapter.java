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


	public EatItemAdapter(Context context, int textViewResourceId, List<EatItem> objects)
	{
		super(context, textViewResourceId, objects);

		mContext = context;
		mEatItems = new ArrayList<EatItem>();
		mEatItems = objects;

	}

	Context mContext;
	List<EatItem> mEatItems;
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view;
		view = convertView;
		
		if (convertView == null)
		{
			LayoutInflater inflater;
			inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_eatlist, parent, false);
		}
		
		EatItem eatItem;
		eatItem = mEatItems.get(position);
		
		// Temporary set image to ab_solid_pinkactionbar
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
