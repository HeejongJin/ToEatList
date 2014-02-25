package com.songjin.toeatlist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

public class TakenNotification
{
	private Context mContext;
	private int mNumber;
	
	private static final int NOTIFICATION_ID = 1;

	public TakenNotification(Context context)
	{
		mContext = context;
		mNumber = 0;
	}

	public void AddTaken(Bitmap takenBitmap)
	{
		long when = System.currentTimeMillis();
		
		// Create new notification
		Notification notification;
		notification = new Notification(R.drawable.ic_launcher, "ToEatList saved", when);
		
		// Set views
		RemoteViews contentView;
		contentView = new RemoteViews(mContext.getPackageName(), R.layout.notification_taken);
		contentView.setImageViewBitmap(R.id.icon, takenBitmap);
		contentView.setTextViewText(R.id.info, "" + (++mNumber));
		notification.contentView = contentView;

		// Set result intent
		Intent notificationIntent;
	    notificationIntent = new Intent(mContext, MainActivity.class);
	    PendingIntent contentIntent;
	    contentIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
	    notification.contentIntent = contentIntent;
		
		// Get notification manager
		NotificationManager notificationManager;
		notificationManager =
			(NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		
		// Show notification
		notificationManager.notify(NOTIFICATION_ID, notification);
	}
}