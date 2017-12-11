package com.example.marwen.projetpidevfinal2017;

/**
 * Created by marwe on 10/12/2017.
 */

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{

    final String tokenPreferenceKey = "fcm_token";

    final static String infoTopicName = "info";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putString(tokenPreferenceKey, FirebaseInstanceId.getInstance().getToken()).apply();

        //FirebaseMessaging.getInstance().subscribeToTopic(infoTopicName);
    }
}