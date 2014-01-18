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
	
	private View mRootView;
	private EatItem mEatItem;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstnaceState)
	{
		Log.d(TAG, "EatListDetailFragment-onCreateView()");
		
		getActivity().getActionBar().setTitle(TITLE);
		
		Bundle args;
		args = getArguments();
		mEatItem = args.getParcelable("EatItem");
		
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_eatlist_detail, container, false);
		mRootView = rootView;
		
		TextView textStorename;
		textStorename = (TextView)mRootView.findViewById(R.id.storename_text);
		textStorename.setText(mEatItem.getStorename());
		
		TextView textMenuname;
		textMenuname = (TextView)mRootView.findViewById(R.id.menuname_text);
		if (0 < mEatItem.getMenunameCount())
		{
			textMenuname.setText(mEatItem.getMenuname(0));
		}
		
		TextView textLocationinfo;
		textLocationinfo = (TextView)mRootView.findViewById(R.id.locationinfo_text);
		textLocationinfo.setText(mEatItem.getLocation().getLocationname());
		
		TextView textURL;
		textURL = (TextView)mRootView.findViewById(R.id.URL_text);
		if (0 < mEatItem.getURLCount())
		{
			textURL.setText(mEatItem.getURL(0));
		}
		
		return mRootView;
	}
}
