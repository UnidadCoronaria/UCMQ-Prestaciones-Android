package com.unidadcoronaria.prestaciones.app.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.adapter.ListMedicalServiceTabAdapter;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.MedicalServiceDetailFragment;
import com.unidadcoronaria.prestaciones.util.location.LocationService;

import java.util.List;

public class MedicalServiceDetailActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment getFragment() {
        return MedicalServiceDetailFragment.newInstance();
    }

    public static Intent getStartIntent(Context context){
        return new Intent(context, MedicalServiceDetailActivity.class);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LocationService.PERMISSION_ACCESS_LOCATION) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                if (fragments != null) {
                    for (Fragment fragment : fragments) {
                        BaseFragment baseFragment = (BaseFragment) fragment;
                        if (baseFragment != null) {
                            baseFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
                        }
                    }
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
        new AlertDialog.Builder(this)
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

    protected boolean showDisplayHomeAsUpEnabled() {
        return true;
    }

    protected boolean showHomeButtonEnable() {
        return true;
    }
}

