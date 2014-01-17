package com.songjin.toeatlist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class EatListFragment extends Fragment
{
	private static final String TAG = "ToEatList";
	
	private View mRootView;
	private ListView mEatList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstnaceState)
	{
		Log.d(TAG, "EatListFragment-onCreateView()");
		
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_eatlist, container, false);
		mRootView = rootView;
		
		mEatList = (ListView)mRootView.findViewById(R.id.eatlist_listview);
		
		/* Listview test data */
		String str[] = 
		{
			"line1", "line2", "line3", "line4",
			"line5", "line6", "line7", "line8"
		};
		
		ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(mRootView.getContext(), android.R.layout.simple_list_item_1, str);
		
		mEatList.setAdapter(adapter);
		
		return rootView;
	}
}
