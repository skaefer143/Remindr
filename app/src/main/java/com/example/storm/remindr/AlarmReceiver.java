package com.example.storm.remindr;

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

public class AlarmReceiver extends BroadcastReceiver {
    //with help from http://androidideasblog.blogspot.ca/2011/07/alarmmanager-and-notificationmanager.html
    //and https://www.tutorialspoint.com/android/android_push_notification.htm
    //and http://stackoverflow.com/questions/31001784/alarmmanager-with-notification-android

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, habitStatsActivity.class), 0);
        // Set the info for the views that show in the notification panel.
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(context)
                .setContentTitle("Reminder")
                .setContentText("You need to eat")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent);        // Send the notification.
        // We use a layout id because it is a unique number. We use it later to cancel.
        notificationManager.notify(1, mNotifyBuilder.build());
    }
}
