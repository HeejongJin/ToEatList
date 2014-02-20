package com.songjin.toeatlist;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FileObserverService extends Service
{
	private static final String TAG = "ToEatList";
	
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	
	@Override
	public void onCreate()
	{
		Log.i(TAG, "FileObserverService-onCreate()");
		
		// Start monitoring
	}
	
	@Override
	public void onDestroy()
	{
		// Stop monitoring
	}
}