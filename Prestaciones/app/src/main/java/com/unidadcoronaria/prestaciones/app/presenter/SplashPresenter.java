package com.unidadcoronaria.prestaciones.app.presenter;

import android.os.CountDownTimer;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.unidadcoronaria.prestaciones.App;
import com.unidadcoronaria.prestaciones.app.SplashView;
import com.unidadcoronaria.prestaciones.app.activity.MainActivity;
import com.unidadcoronaria.prestaciones.app.fragment.SplashFragment;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.util.SharedPreferencesHelper;

/**
 * @author Agustin.Bala
 * @since 3.0.0
 */
public class SplashPresenter extends BasePresenter<SplashView>{

    //region Constants
    private static final int SPLASH_DURATION = 2 * 1000;
    //endregion

    private int total = 0;

    public SplashPresenter(SplashView view) {
        super(view);
    }

    public void initSplash(){
        if(isGooglePlayServicesAvailable()) {
        view.onProgressUpdate(total);
            new CountDownTimer(SPLASH_DURATION, 20) {
                public void onTick(long millisUntilFinished) {
                    total += 2;
                    view.onProgressUpdate(total);
                }

                @Override
                public void onFinish() {
                    total = 0;
                    SharedPreferencesHelper.putBoolean(App.getInstance(), "SPLASH_FINISH", true);
                    if(SharedPreferencesHelper.getBoolean(App.getInstance(), "IMEI")) {
                       view.callNextActivity();
                       SharedPreferencesHelper.putBoolean(App.getInstance(), "SPLASH_FINISH", false);
                       SharedPreferencesHelper.putBoolean(App.getInstance(), "IMEI", false);
                    }
                }
            }.start();
       }
    }



    private boolean isGooglePlayServicesAvailable() {
        int statusCode = GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(App.getInstance());

        if (GoogleApiAvailability.getInstance().isUserResolvableError(statusCode)) {
            view.showGooglePlayServicesDialog(statusCode);
            return false;
        } else if (statusCode != ConnectionResult.SUCCESS) {
            view.showGooglePlayServicesError();
            return false;
        }

        return true;
    }

}
