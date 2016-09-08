package com.unidadcoronaria.prestaciones.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.adapter.ListMedicalServiceTabAdapter;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.MedicalServiceDetailFragment;

public class MedicalServiceDetailActivity extends BaseDrawerActivity {


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

    @Override
    protected int getHeaderView() {
        return 0;
    }

    @Override
    protected int getDrawerMenu() {
        return 0;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public static Intent getStartIntent(Context context){
        return new Intent(context, MedicalServiceDetailActivity.class);
    }

    protected boolean showDisplayHomeAsUpEnabled() {
        return true;
    }

    protected boolean showHomeButtonEnable() {
        return true;
    }
}

