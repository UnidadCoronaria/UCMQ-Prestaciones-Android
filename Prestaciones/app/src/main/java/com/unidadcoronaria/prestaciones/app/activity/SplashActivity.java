package com.unidadcoronaria.prestaciones.app.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;

import com.crashlytics.android.Crashlytics;
import com.unidadcoronaria.prestaciones.BuildConfig;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.SplashFragment;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;

import io.fabric.sdk.android.Fabric;

/**
 * The first activity where we do some validations and show  the home.
 *
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class SplashActivity extends BaseActivity {

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 111;

    //region BaseActivity implementation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }

        hideToolbar();
    }

    @Override
    protected Boolean locationEnabled() {
        return false;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    protected BaseFragment getFragment() {
        return null;
    }

    @Override
    public void onResume(){
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                        PERMISSIONS_REQUEST_READ_PHONE_STATE);
            } else {
                saveIMEI();
            }
        } else {
            saveIMEI();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (grantResults.length > 0 && requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            saveIMEI();
        }
    }


    private void saveIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        ApiClient.IMEI = telephonyManager.getDeviceId().toString();
        new Handler().post(new Runnable() {
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.activity_base_fragment, SplashFragment.newInstance()).commit();
            }
        });

    }


    //endregion
}