package com.example.marwen.projetpidevfinal2017;

/**
 * Created by marwe on 10/12/2017.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getFrom().equals("/topics/" + MyFirebaseInstanceIDService.infoTopicName)){
            displayNotifcation(remoteMessage.getData().get("title"), remoteMessage.getData().get("content-text"));
        }
    }

    private void displayNotifcation(String title, String contentText){
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(contentText)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(14, notification);
        //Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
    }
}