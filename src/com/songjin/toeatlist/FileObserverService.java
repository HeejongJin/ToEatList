package com.songjin.toeatlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.FileObserver;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

public class FileObserverService extends Service
								 implements RecursiveFileObserver.OnFileEventListener
{
	private static final String TAG = "ToEatList";
	
	private List<RecursiveFileObserver> mObservers;
	private RecursiveFileObserver.OnFileEventListener mFileEventListener;
	
	/**
	 * Similar to android.os.Environment.getExternalStorageDirectory(), except
	 * that here, we return all possible storage directories. The Environment
	 * class only returns one storage directory. If you have an extended SD
	 * card, it does not return the directory path. Here we are trying to return
	 * all of them.
	 * 
	 * @return
	 */
	public static String[] getStorageDirectories()
	  {
	      String[] dirs = null;
	      BufferedReader bufReader = null;
	      
	      try
	      {
	          bufReader = new BufferedReader(new FileReader("/proc/mounts"));
	          ArrayList<String> list = new ArrayList<String>();
	          String line;
	          
	          while ((line = bufReader.readLine()) != null)
	          {
	              if (line.contains("vfat") || line.contains("/mnt"))
	              {
	                  StringTokenizer tokens = new StringTokenizer(line, " ");
	                  String s = tokens.nextToken();
	                  s = tokens.nextToken(); // Take the second token, i.e. mount point

	                  if (s.equals(Environment.getExternalStorageDirectory().getPath()))
	                  {
	                      list.add(s);
	                  }
	                  else if (line.contains("/dev/block/vold"))
	                  {
	                      if (!line.contains("/mnt/secure") && !line.contains("/mnt/asec") && !line.contains("/mnt/obb") && !line.contains("/dev/mapper") && !line.contains("tmpfs"))
	                      {
	                          list.add(s);
	                      }
	                  }
	              }
	          }

	          dirs = new String[list.size() + 1];
	          dirs[0] = Environment.getExternalStorageDirectory().getAbsolutePath();
	          
	          for (int i=0 ; i<list.size() ; i++)
	          {
	              dirs[i+1] = list.get(i);
	          }
	      }
	      catch (FileNotFoundException e) {}
	      catch (IOException e) {}
	      finally
	      {
	          if (bufReader != null)
	          {
	              try
	              {
	                  bufReader.close();
	              }
	              catch (IOException e) {}
	          }
	      }

	      return dirs;
	  }
	
	@Override
	public IBinder onBind(Intent intent)
	{	
		return null;
	}
	
	@Override
	public void onCreate()
	{
		Log.i(TAG, "FileObserverService-onCreate()");
		
		if (mObservers != null)
		{
			return ;
		}
		
		mObservers = new ArrayList<RecursiveFileObserver>();
		
		// Get all root path of internal and external storage
		String[] pathTarget;
		pathTarget = getStorageDirectories();
		
		// Monitor all the storages
		for (int i=0 ; i<pathTarget.length ; i++)
		{
			RecursiveFileObserver observer;
			observer = new RecursiveFileObserver(pathTarget[i], FileObserver.CREATE);
			
			observer.startWatching();
			observer.setOnFileEventListener(this);
			mObservers.add(observer);
		}		
	}
	
	@Override
	public void onDestroy()
	{
		// Stop monitoring
		for (int i=0 ; i<mObservers.size() ; i++)
		{
			mObservers.get(i).stopWatching();
		}
		mObservers.clear();
		mObservers = null;
	}

	@Override
	public void onFileEvent(int event, String path)
	{
		switch (event)
		{
		case FileObserver.CREATE:
			Log.i(TAG, "onFileEvent()-FileObserver.Create" + path);
			
			// Get file extension
			String fileExt;
			fileExt = path.substring((path.lastIndexOf(".") + 1), path.length());
			
			// Check whether file extension is image file
			boolean isImage;
			isImage =
				fileExt.equals("jpg") |
				fileExt.equals("jpeg") |
				fileExt.equals("png");
			if (!isImage)
			{
				break;
			}
			
			// Check correct image file
			File imgFile;
			imgFile = new File(path);
			if (!imgFile.exists())
			{
				break;
			}
				
			break;
		}
	}
}