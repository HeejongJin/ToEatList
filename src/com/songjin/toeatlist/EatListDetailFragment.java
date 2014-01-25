package com.songjin.toeatlist;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EatListDetailFragment extends Fragment
{
	private static final String TAG = "ToEatList";
	private static final String TITLE = "I ... MUST EAT THIS";
	
	private MainActivity mRootActivity;
	private View mRootView;
	private EatItem mEatItem;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstnaceState)
	{
		Log.i(TAG, "EatListDetailFragment-onCreateView()");
		
		// Get root activity
		mRootActivity = (MainActivity)getActivity();
		
		// Set title
		mRootActivity.setTitle(TITLE);
		
		// Get root view
		mRootView = inflater.inflate(R.layout.fragment_eatlist_detail, container, false);
		
		// Get arguments
		EatItem eatItem;
		eatItem = getArguments().getParcelable("EatItem");
		
		// Update data from arguments
		updateData(eatItem);
		
		return mRootView;
	}
	
	private void updateData(EatItem eatItem)
	{
		// Fill view data
		TextView textStorename;
		textStorename = (TextView)mRootView.findViewById(R.id.storename_text);
		textStorename.setText(eatItem.getStorename());
		
		TextView textMenuname;
		textMenuname = (TextView)mRootView.findViewById(R.id.menuname_text);
		if (0 < eatItem.getMenunameCount())
		{
			textMenuname.setText(eatItem.getMenuname(0));
		}
		
		TextView textLocationinfo;
		textLocationinfo = (TextView)mRootView.findViewById(R.id.locationinfo_text);
		textLocationinfo.setText(eatItem.getLocation().getLocationname());
		
		TextView textURL;
		textURL = (TextView)mRootView.findViewById(R.id.URL_text);
		if (0 < eatItem.getURLCount())
		{
			textURL.setText(eatItem.getURL(0));
		}
		
		// Sync member variable(mEatItem)
		mEatItem = eatItem;
	}
}
