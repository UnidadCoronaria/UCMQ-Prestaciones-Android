package com.unidadcoronaria.prestaciones;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.telephony.TelephonyManager;

import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.util.SharedPreferencesHelper;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class App extends MultiDexApplication {


    private static App INSTANCE;
    //region Public Static Implementation
    public static App getInstance() {
        return INSTANCE;
    }
    //endregion

    //region Application
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }



}