package com.unidadcoronaria.prestaciones.app.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.util.location.LocationService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The base class for all activities
 *
 * @author Agustin.Bala
 * @since 0.0.1
 */
public abstract class BaseActivity extends AppCompatActivity implements LocationService.Callback {

    @BindView(R.id.toolbar)
    @Nullable
    Toolbar vToolbar;
    private LocationService mLocationService;

    //region Lifecycle implementation
    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        configureToolbar(savedInstanceState);
        if (savedInstanceState == null && getFragment() != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_base_fragment, getFragment()).commit();
        }
        if(locationEnabled()) {
            mLocationService = new LocationService(this);
            mLocationService.onCreate();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(locationEnabled()) {
            mLocationService.onResume();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(locationEnabled()) {
            mLocationService.onPause();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(locationEnabled()) {
            mLocationService.onDestroy();
        }
    }
    //endregion

    //region Toolbar
    protected void configureToolbar(Bundle savedInstanceState) {
        setSupportActionBar(vToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    protected void hideToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    protected abstract Boolean locationEnabled();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LocationService.PERMISSION_ACCESS_LOCATION) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                if(locationEnabled()) {
                    mLocationService.onResume();
                }

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        showNeedLocationPermissionMessage();
                    }
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void showNeedLocationPermissionMessage() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.notice))
                .setMessage(getString(R.string.need_gps_permission_message))
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .show();
    }

    //region Abstract methods
    protected abstract @LayoutRes int getLayout();

    protected abstract BaseFragment getFragment();

    @Override
    public void onLocationChanged(Location location) {
        //TODO Do extra stuffs besides to store location on shared preferences
    }

    //endregion
}