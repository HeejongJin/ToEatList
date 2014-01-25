package com.songjin.toeatlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;


public class EatListFragment extends Fragment
{
	private static final String TAG = "ToEatList";
	private static final String TITLE = "TO EAT LIST FOR YOU";
	
	private View mRootView;
	private ListView mEatList;
	private ImageButton mButtonAdd;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstnaceState)
	{
		Log.d(TAG, "EatListFragment-onCreateView()");
		
		getActivity().getActionBar().setTitle(TITLE);
		
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_eatlist, container, false);
		mRootView = rootView;
		
		mEatList = (ListView)mRootView.findViewById(R.id.eatlist_listview);
		mButtonAdd = (ImageButton)mRootView.findViewById(R.id.eatlist_add);
		
		/* Listview test data */	
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

		EatItemAdapter adapter;
		adapter = new EatItemAdapter(mRootView.getContext(), R.layout.item_eatlist, eatItems);
		
		mEatList.setAdapter(adapter);
		
		mEatList.setOnItemClickListener
		(
			new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					EatItem eatItem;
					eatItem = (EatItem)mEatList.getItemAtPosition(position);
					
		            // update the main content by replacing fragments
		            Fragment fragment = new EatListDetailFragment();
		            
		            Bundle args;
		            args = new Bundle();
		            args.putParcelable("EatItem", eatItem);
		            
		            fragment.setArguments(args);

		            FragmentTransaction transaction;
		            transaction = getFragmentManager().beginTransaction();
		            transaction.replace(R.id.content_frame, fragment);
		            transaction.addToBackStack("EatListFragment");
		            transaction.commit();
				}
			}
		);
		
		mButtonAdd.setOnClickListener
		(
			new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Fragment fragment;
					fragment = new EatListAddFragment();
					
					FragmentTransaction transaction;
					transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.content_frame, fragment);
					transaction.addToBackStack("EatListFragment");
					transaction.commit();
				}
			}
		);
		
		return mRootView;
	}
}
