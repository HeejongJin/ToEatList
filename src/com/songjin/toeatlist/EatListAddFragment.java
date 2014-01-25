package com.songjin.toeatlist;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EatListAddFragment extends Fragment
{
	private static final String TAG = "ToEatList";
	private static final String TITLE = "TO EAT LIST FOR YOU";
	
	private View mRootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstnaceState)
	{
		Log.d(TAG, "EatListFragment-onCreateView()");
		
		getActivity().getActionBar().setTitle(TITLE);
		
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_eatlist_add, container, false);
		mRootView = rootView;
		
		return mRootView;
	}
}
