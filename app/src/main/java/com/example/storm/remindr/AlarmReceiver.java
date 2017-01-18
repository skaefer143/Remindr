package com.example.storm.remindr;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Storm on 2017-01-17.
 */

public class AlarmReceiver extends IntentService {
    //with help from http://androidideasblog.blogspot.ca/2011/07/alarmmanager-and-notificationmanager.html
    //and https://www.tutorialspoint.com/android/android_push_notification.htm
    //and http://stackoverflow.com/questions/31001784/alarmmanager-with-notification-android

    public AlarmReceiver(){
        super("AlarmReceiver");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
        // Set the info for the views that show in the notification panel.
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
            builder.setAutoCancel(false);
            builder.setTicker("this is ticker text");
            builder.setContentTitle("WhatsApp Notification");
            builder.setContentText("You have a new message");
            builder.setSmallIcon(android.R.drawable.ic_menu_gallery);
            builder.setContentIntent(pendingIntent);
            builder.setOngoing(true);
            builder.setSubText("This is subtext...");   //API level 16
            builder.setNumber(100);
            builder.build();        // Send the notification.

        // We use a layout id because it is a unique number. We use it later to cancel.
        notificationManager.notify(1, builder.getNotification());
    }
}
