package com.songjin.toeatlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class EatListFragment extends Fragment
{
	private static final String TAG = "ToEatList";
	private static final String TITLE = "TO EAT LIST FOR YOU";
	
	private MainActivity mRootActivity;
	private View mRootView;
	private ListView mEatList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstnaceState)
	{
		Log.i(TAG, "EatListFragment-onCreateView()");
		
		// Get root activity
		mRootActivity = (MainActivity)getActivity();
		
		// Set title
		mRootActivity.setTitle(TITLE);
		
		// Get root view
		mRootView = inflater.inflate(R.layout.fragment_eatlist, container, false);
		
		// Get views
		mEatList = (ListView)mRootView.findViewById(R.id.eatlist_listview);
		
		/* EatList test data */
		List<EatItem> eatItems;
		eatItems = new ArrayList<EatItem>();
		
		EatItem eatItem;
		
		eatItem = new EatItem("STORE_NAME", new GeoItem("LOCATION_INFO", new GeoPoint()));
		eatItem.addMenuName("MENU_NAME");
		eatItem.addURL("BLOG.NAVER.COM/HEEJONGJIN/210");
		eatItems.add(eatItem);
		
		eatItem = new EatItem("Store1", new GeoItem("Location1", new GeoPoint()));
		eatItem.addMenuName("Menu1");
		eatItem.addURL("BLOG.NAVER.COM/HEEJONGJIN/211");
		eatItems.add(eatItem);
		
		eatItem = new EatItem("Store2", new GeoItem("Location2", new GeoPoint()));
		eatItem.addMenuName("Menu2");
		eatItem.addURL("BLOG.NAVER.COM/HEEJONGJIN/212");
		eatItems.add(eatItem);
		
		eatItem = new EatItem("Store3", new GeoItem("Location3", new GeoPoint()));
		eatItem.addMenuName("Menu3");
		eatItem.addURL("BLOG.NAVER.COM/HEEJONGJIN/213");
		eatItems.add(eatItem);
		
		eatItem = new EatItem("Store4", new GeoItem("Location4", new GeoPoint()));
		eatItem.addMenuName("Menu4");
		eatItem.addURL("BLOG.NAVER.COM/HEEJONGJIN/214");
		eatItems.add(eatItem);

		// Create adapter for eat list
		EatItemAdapter adapter;
		adapter = new EatItemAdapter(mRootView.getContext(), R.layout.item_eatlist, eatItems);
		
		// Set adapter
		mEatList.setAdapter(adapter);
		
		// Set listeners
		mEatList.setOnItemClickListener
		(
			new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					// Get data of selected item
					EatItem eatItem;
					eatItem = (EatItem)mEatList.getItemAtPosition(position);
					
					// Create arguments and put data
		            Bundle args;
		            args = new Bundle();
		            args.putParcelable("EatItem", eatItem);
		            
					// Replace fragment
					mRootActivity.replaceFragment(true, R.layout.fragment_eatlist_detail, args);
				}
			}
		);
		
		return mRootView;
	}
}
