package com.unidadcoronaria.prestaciones.app.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.unidadcoronaria.domain.usecase.UpdateFCMTokenUseCase;
import com.unidadcoronaria.prestaciones.App;
import com.unidadcoronaria.prestaciones.util.SharedPreferencesHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by AGUSTIN.BALA on 11/12/2016.
 */

public class FCMInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "FCMInstanceIdService";
    private UpdateFCMTokenUseCase mUpdateFCMTokenUseCase;

    public FCMInstanceIdService() {
        mUpdateFCMTokenUseCase = new UpdateFCMTokenUseCase();
    }

    @Override
    public void onTokenRefresh() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        EventBus.getDefault().register(this);
        Log.d(TAG, "FCM Token: " + fcmToken);
        SharedPreferencesHelper.putString(App.getInstance(), "FCM_TOKEN", fcmToken);
        sendTokenToServer(fcmToken);
    }

    private void sendTokenToServer(String fcmToken) {
        mUpdateFCMTokenUseCase.setData(fcmToken);
        mUpdateFCMTokenUseCase.execute(App.getInstance());
    }

    @Subscribe
    public void onError(UpdateFCMTokenUseCase.ErrorResponse errorResponse){
        Log.e(TAG, "Error saving fcm token");
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSuccess(UpdateFCMTokenUseCase.SuccessResponse successResponse){
        Log.d(TAG, "Success saving fcm token");
        EventBus.getDefault().unregister(this);
    }
}
