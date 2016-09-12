package com.unidadcoronaria.prestaciones.util.location;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class LocationService {

    public static final int PERMISSION_ACCESS_LOCATION = 1;

    private static LocationService instance = null;

    private LocationManager locationManager;

    public static LocationService getInstance() {
        if (instance == null) {
            instance = new LocationService();
        }
        return instance;
    }

    /**
     * Local constructor
     */
    private LocationService() {
        Log.d(getClass().getSimpleName(), "LocationService created");
    }


    /**
     * Sets up location service after permissions is granted
     */
    public void initLocationService(Context activity, final Callback mCallback) {
        if (activity instanceof Activity) {
            if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) activity, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, LocationService.PERMISSION_ACCESS_LOCATION);
                return;
            }
        }

        try {
            this.locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

            if(locationManager != null){
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                locationManager.requestSingleUpdate(criteria, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        mCallback.onLocationChanged(location);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        Log.e("LocationService", "onStatusChanged - "+provider+" - "+status);
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        Log.e("LocationService", "onProviderEnabled - "+provider);
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        Log.e("LocationService", "onProviderDisabled - "+provider);
                    }
                }, null);
            }
        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), "Error creating location service", ex);
        }

    }


    public interface Callback{
        void onLocationChanged(Location location);
    }

}