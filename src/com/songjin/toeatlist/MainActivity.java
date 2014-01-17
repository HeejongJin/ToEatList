package com.songjin.toeatlist;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity
{
	private static final String TAG = "ToEatList";
	
	private String[] mLeftDrawerTitles;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mLeftDrawerToggle;
	private ListView mLeftDrawerList;
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener
	{
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id)
	    {
	    	
	    }
	}
	
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
		Log.d(TAG, "onCreate()");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mLeftDrawerTitles = getResources().getStringArray(R.array.left_drawer_array);
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
	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView)
	            {
	                super.onDrawerOpened(drawerView);
	            }
	            
				/** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view)
	            {
	                super.onDrawerClosed(view);
	            }
			};

	    // Set the drawer toggle as the DrawerListener
	    mDrawerLayout.setDrawerListener(mLeftDrawerToggle);
	    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    getActionBar().setHomeButtonEnabled(true);
	    
		mLeftDrawerList = (ListView)findViewById(R.id.left_drawer);
		
        // Set the adapter for the list view
		mLeftDrawerList.setAdapter
		(
			new ArrayAdapter<String>
			(
				this,
				R.layout.left_drawer_list_item,
				mLeftDrawerTitles
			)
		);
		
        // Set the list's click listener
		mLeftDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
        if (savedInstanceState == null)
        {
            // update the main content by replacing fragments
            Fragment fragment = new EatListFragment();

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
	}
	
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mLeftDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mLeftDrawerToggle.onOptionsItemSelected(item))
        {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
