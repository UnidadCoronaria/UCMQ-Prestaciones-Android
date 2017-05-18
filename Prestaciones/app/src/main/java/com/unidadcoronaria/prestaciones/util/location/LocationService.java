package com.unidadcoronaria.prestaciones.util.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.unidadcoronaria.prestaciones.util.LocationHelper;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class LocationService implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static final int PERMISSION_ACCESS_LOCATION = 1;
    private GoogleApiClient mGoogleApiClient;
    private boolean mRequestingLocationUpdates = Boolean.FALSE;
    private Callback mCallback;

    public LocationService(final Callback mCallback) {
        this.mCallback = mCallback;
        createGoogleApiInstance((Context) mCallback);
    }
    /**
     * Sets up location service after permissions is granted
     */
    public void initLocationService() {
        try {
            Log.i("onLocationChanged", mCallback.toString() + " initLocationService");
            if (mCallback instanceof Activity) {
                if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission((Context) mCallback, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission((Context) mCallback, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mCallback, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, LocationService.PERMISSION_ACCESS_LOCATION);
                    return;
                }
            }
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(60 * 1000);
            mLocationRequest.setFastestInterval(30 * 1000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            mRequestingLocationUpdates = true;
        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), "Error creating location service", ex);
        }

    }

    private void createGoogleApiInstance(Context activity) {
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            Log.i("onLocationChanged", mCallback.toString() + " createGoogleApiInstance");
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("onLocationChanged", mCallback.toString() + " onConnected");
        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
            initLocationService();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    //region Public implementation
    public void onCreate(){
        if(!mGoogleApiClient.isConnected()){
            mGoogleApiClient.connect();
        }
    }

    public void onResume(){
        Log.i("onLocationChanged",mCallback.toString() +  " onResume");
        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
            initLocationService();
        } else {
            if(!mGoogleApiClient.isConnected()){
                Log.i("onLocationChanged", mCallback.toString() + " onResume - Google Api is not connected");
            } else {
                Log.i("onLocationChanged", mCallback.toString() + " onResume - There is a location listener running");
            }
        }
    }

    public void onPause(){
        Log.i("onLocationChanged", mCallback.toString() + " onPause");
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            Log.i("onLocationChanged", mCallback.toString() + " Removing location listener");
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
            mRequestingLocationUpdates = false;
        } else {
            Log.i("onLocationChanged", mCallback.toString() + " There is no location listener");
        }
    }

    public void onDestroy(){
        if(mGoogleApiClient.isConnected()) {
            Log.i("onLocationChanged", mCallback.toString() + " onStop");
            mGoogleApiClient.disconnect();
        }
    }
    //endregion

    @Override
    public void onLocationChanged(Location location) {
        Log.i("onLocationChanged", "Nueva localizacion obtenida "+ location.getLatitude()+ " - "+location.getLongitude()+" - "+location.getProvider()+" - "+location.getAccuracy()  );
        LocationHelper.saveLocation(location);
        mCallback.onLocationChanged(location);
    }

    public interface Callback{
        void onLocationChanged(Location location);
    }

}