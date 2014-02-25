package com.songjin.toeatlist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.FileObserver;
import android.util.Log;

public class RecursiveFileObserver extends FileObserver
{	
	public interface OnFileEventListener
	{
		public void onFileEvent(int event, String path);
	}
	
	private OnFileEventListener mFileEventListener;
	private List<SingleFileObserver> mObservers;
	private String mPath;
	private int mMask;

	public RecursiveFileObserver(String path)
	{
		this(path, ALL_EVENTS);
	}
	
	public RecursiveFileObserver(String path, int mask)
	{
		super(path, mask);
		mPath = path;
		mMask = mask;
	}
	
	public void setOnFileEventListener(OnFileEventListener listener)
	{
		mFileEventListener = listener;
	}
	
	@Override
	public void startWatching()
	{
	    if (mObservers != null)
	    {
	    	return ;
	    }
	    
	    mObservers = new ArrayList<SingleFileObserver>();
	    Stack<String> stack = new Stack<String>();
	    stack.push(mPath);

	    // Loop all sub directories
	    while (!stack.empty())
	    {
	    	String parent = stack.pop();
	    	mObservers.add(new SingleFileObserver(parent, mMask));
	    	
	    	File path;
	    	path = new File(parent);
	    	
	    	File[] subFiles;
	    	subFiles = path.listFiles();
	    	
	    	if (subFiles == null)
	    	{
	    		continue;
	    	}
	    	
	    	for (int i=0 ; i<subFiles.length ; i++)
	    	{
	    		if (subFiles[i].isDirectory() &&
	    			!subFiles[i].getName().equals(".") &&
	    			!subFiles[i].getName().equals(".."))
	    		{
	                stack.push(subFiles[i].getPath());
	            }
	        }
	    }
	    
	    // Start all got directories
	    for (int i=0 ; i<mObservers.size() ; i++)
	    {
	    	mObservers.get(i).startWatching();
	    }
	}

	@Override
	public void stopWatching()
	{
	    if (mObservers == null)
	    {
	    	return;
	    }

	    for (int i=0 ; i<mObservers.size() ; i++)
	    {
	    	mObservers.get(i).stopWatching();
	    }

	    mObservers.clear();
	    mObservers = null;
	}

	@Override
	public void onEvent(int event, String path)
	{
		if (mFileEventListener != null)
		{
			mFileEventListener.onFileEvent(event, path);
		}
	}
	
	private class SingleFileObserver extends FileObserver
	{
		private String mPath;

	    public SingleFileObserver(String path, int mask)
	    {
	    	super(path, mask);
	    	mPath = path;
	    }

	    @Override
	    public void onEvent(int event, String path)
	    {
	        String newPath = mPath + "/" + path;
	        RecursiveFileObserver.this.onEvent(event, newPath);
	    }
	}
}