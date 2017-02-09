package com.unidadcoronaria.prestaciones.app.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.unidadcoronaria.prestaciones.App;
import com.unidadcoronaria.prestaciones.util.SharedPreferencesHelper;

/**
 * Created by AGUSTIN.BALA on 11/12/2016.
 */

public class FCMInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "FCMInstanceIdService";

    public FCMInstanceIdService() {
    }

    @Override
    public void onTokenRefresh() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Token: " + fcmToken);
        SharedPreferencesHelper.putString(App.getInstance(), "FCM_TOKEN", fcmToken);
        sendTokenToServer(fcmToken);
    }

    private void sendTokenToServer(String fcmToken) {
        // Acciones para enviar token a tu app server
    }
}
