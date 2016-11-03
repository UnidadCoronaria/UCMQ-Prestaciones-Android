package com.unidadcoronaria.prestaciones.app.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.SupplyFragment;
import com.unidadcoronaria.prestaciones.app.fragment.WatchFragment;
import com.unidadcoronaria.prestaciones.app.presenter.MainDrawerPresenter;

public class WatchActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.init_watch));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_base_with_drawer;
    }

    @Override
    protected BaseFragment getFragment() {
        return WatchFragment.newInstance();
    }

    public static Intent getStartIntent(Context context){
        return new Intent(context, WatchActivity.class);
    }

    @Override
    protected int getHeaderView() {
        return R.layout.activity_main_drawer_header;
    }

    @Override
    protected int getDrawerMenu() {
        return R.menu.activity_main_drawer;
    }

    protected boolean showDisplayHomeAsUpEnabled() {
        return true;
    }

    protected boolean showHomeButtonEnable() {
        return true;
    }

}
