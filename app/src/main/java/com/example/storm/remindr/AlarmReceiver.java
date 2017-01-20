package com.example.storm.remindr;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Storm on 2017-01-17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    //with help from http://androidideasblog.blogspot.ca/2011/07/alarmmanager-and-notificationmanager.html
    //and https://www.tutorialspoint.com/android/android_push_notification.htm
    //and http://stackoverflow.com/questions/31001784/alarmmanager-with-notification-android

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        setNextAlarm(); //set the next alarm we must fire

        //TODO: build a stack to the actual habit page, instead of mainActivity?
        Intent notificationIntent = new Intent(context, MainActivity.class);
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        // Set the info for the views that show in the notification panel.
        Notification.Builder builder = new Notification.Builder(context);
            builder.setAutoCancel(true);
            builder.setContentTitle("Remindr Notification");
            builder.setContentText("Reminder to complete activity!");
            builder.setSmallIcon(R.color.colorPrimary);
            builder.setContentIntent(pendingIntent);
            builder.setOngoing(false);   //API level 16

        // We use a layout id because it is a unique number. We use it later to cancel.
        notificationManager.notify(1, builder.build()); // Send the notification.
    }

    public void setNextAlarm(){
        //TODO: make setNextAlarm()
        //need to pull up all of our habits, and then check all their times and dates, see which
        //one is coming next, and then set the alarm at that time


    }
}
