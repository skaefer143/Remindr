package com.example.storm.remindr;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Storm on 2017-01-16.
 */

public class ReminderService extends IntentService {
    //with help from http://stackoverflow.com/questions/7540724/how-to-start-notification-on-custom-datetime

    private static final int NOTIF_ID = 1;

    public ReminderService(){
        super("ReminderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        long when = System.currentTimeMillis();         // notification time
        Notification notification = new Notification(R.drawable.icon, "reminder", when);
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.flags |= notification.FLAG_AUTO_CANCEL;
        Intent notificationIntent = new Intent(this, YourActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent , 0);
        notification.setLatestEventInfo(getApplicationContext(), "It's about time", "You should open the app now", contentIntent);
        nm.notify(NOTIF_ID, notification);
    }

}