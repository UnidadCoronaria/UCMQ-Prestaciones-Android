package com.unidadcoronaria.prestaciones.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.unidadcoronaria.prestaciones.App;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.util.location.LocationService;

/**
 * Created by AGUSTIN.BALA on 12/10/2016.
 */

public class LocationHelper {

    private static AlertDialog alertDialog;

    public static void saveLocation(Location location){
        SharedPreferencesHelper.putString(App.getInstance(), "LAT",String.valueOf(location.getLatitude()));
        SharedPreferencesHelper.putString(App.getInstance(), "LON", String.valueOf(location.getLongitude()));
    }

    public static String getLatitude(){
       return SharedPreferencesHelper.getString(App.getInstance(), "LAT", "0.0");
    }

    public static String getLongitude(){
       return SharedPreferencesHelper.getString(App.getInstance(), "LON", "0.0");
    }

    public static Boolean isLocationEnabled(final Activity activity) {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, LocationService.PERMISSION_ACCESS_LOCATION);
            return false;
        }
        LocationManager lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        if (!gps_enabled && !network_enabled) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            dialog.setCancelable(false);
            dialog.setMessage(activity.getResources().getString(R.string.gps_network_not_enabled));
            dialog.setPositiveButton(activity.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    activity.startActivity(myIntent);
                }
            });
            alertDialog = dialog.show();
            return false;
        } else {
            return true;
        }
    }

}
