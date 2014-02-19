package com.songjin.toeatlist;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity
{
	private static final String TAG = "ToEatList";
	
	private static int mFragmentResID;
	
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mLeftDrawerToggle;
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mLeftDrawerToggle.syncState();
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.i(TAG, "MainActivity-onCreate()");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Get views
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		
		// Create new drawer toggle
		mLeftDrawerToggle =
			new ActionBarDrawerToggle
			(
				this,						/* Host activity */
				mDrawerLayout,				/* DrawerLayout object */
				R.drawable.ic_drawer,		/* Navigation drawer icon */
				R.string.left_drawer_open,	/* Open drawer description */
				R.string.left_drawer_close	/* Close drawer description */
			)
			{
	            public void onDrawerOpened(View drawerView)
	            {
	                super.onDrawerOpened(drawerView);
	            }
	            
	            public void onDrawerClosed(View view)
	            {
	                super.onDrawerClosed(view);
	            }
			};

	    // Set the drawer listener
	    mDrawerLayout.setDrawerListener(mLeftDrawerToggle);
	    
	    // Enable ActionBar icon to behave as action to toggle navigation drawer
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    getActionBar().setHomeButtonEnabled(true);
		
        if (savedInstanceState == null)
        {
        	replaceFragment(false, R.layout.fragment_eatlist, null);
        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Get menu inflater
		MenuInflater inflater;
		inflater = getMenuInflater();
		
		switch (mFragmentResID)
		{
		case R.layout.fragment_eatlist:
			// Inflate the menu
			// This add items to the action bar
			inflater.inflate(R.menu.eat_list_fragment_actions, menu);
			break;
			
		case R.layout.fragment_eatlist_detail:
			break;
			
		case R.layout.fragment_map:
			break;
			
		default:
			return true;
		}
		
		return super.onCreateOptionsMenu(menu);
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mLeftDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		
		// Handle action buttons
		switch (item.getItemId())
		{
		case R.id.action_view_as_map:
			// Swap fragment
			replaceFragment(false, R.layout.fragment_map, null);
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
    }
	
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        
        mLeftDrawerToggle.onConfigurationChanged(newConfig);
    }

	public void replaceFragment(boolean isAddBackStack, int fragmentResID, Bundle args)
	{
		Fragment fragment;
		
		switch (fragmentResID)
		{
		case R.layout.fragment_eatlist:
			// Create new fragment
			fragment = new EatListFragment();
			break;
			
		case R.layout.fragment_eatlist_detail:
			fragment = new EatListDetailFragment();
			break;
			
		case R.layout.fragment_map:
			fragment = new MapFragment();
			break;
			
		default:
			return ;
		}
		
		// Put arguments
		if (args != null)
		{
			fragment.setArguments(args);
		}
		
		// Replace the main content in activity		
		FragmentTransaction fragmentTransaction;
		fragmentTransaction = getFragmentManager().beginTransaction();
		if (isAddBackStack)
		{
			fragmentTransaction.replace(R.id.content_frame, fragment);
			fragmentTransaction.addToBackStack("fragment_" + fragmentResID);
		}
		else
		{
			fragmentTransaction.replace(R.id.content_frame, fragment);
		}
		fragmentTransaction.commit();
		
		// Sync member variable(mFragmentResId)
		mFragmentResID = fragmentResID;
	}
}
