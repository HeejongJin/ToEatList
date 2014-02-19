package com.songjin.toeatlist;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapFragment extends Fragment
{
	private static final String TAG = "ToEatList";
	private static final String TITLE = "";
	
	private MainActivity mRootActivity;
	private View mRootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstnaceState)
	{
		Log.i(TAG, "MapFragment-onCreateView()");
		
		// Get root activity
		mRootActivity = (MainActivity)getActivity();
		
		// Set title
		mRootActivity.setTitle(TITLE);
		
		// Get root view
		mRootView = inflater.inflate(R.layout.fragment_map, null, false);
				
		return mRootView;
	}
}
