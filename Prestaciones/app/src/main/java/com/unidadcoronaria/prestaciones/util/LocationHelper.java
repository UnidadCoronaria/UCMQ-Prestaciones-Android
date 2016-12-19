package com.unidadcoronaria.prestaciones.util;

import android.location.Location;

import com.unidadcoronaria.prestaciones.App;

/**
 * Created by AGUSTIN.BALA on 12/10/2016.
 */

public class LocationHelper {

    public static void saveLocation(Location location){
        SharedPreferencesHelper.putString(App.getInstance(), "LAT",String.valueOf(location.getLatitude()));
        SharedPreferencesHelper.putString(App.getInstance(), "LON", String.valueOf(location.getLongitude()));
    }

    public static String getLatitude(){
       return SharedPreferencesHelper.getString(App.getInstance(), "LAT");
    }

    public static String getLongitude(){
       return SharedPreferencesHelper.getString(App.getInstance(), "LON");
    }
}
