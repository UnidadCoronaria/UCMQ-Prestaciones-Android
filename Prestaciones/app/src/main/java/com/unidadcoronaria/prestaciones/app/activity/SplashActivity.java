package com.unidadcoronaria.prestaciones.app.activity;

import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.unidadcoronaria.prestaciones.BuildConfig;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.SplashFragment;
import io.fabric.sdk.android.Fabric;

/**
 * The first activity where we do some validations and show  the home.
 *
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class SplashActivity extends BaseActivity {

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
    protected int getLayout() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment getFragment() {
        return SplashFragment.newInstance();
    }

    //endregion
}