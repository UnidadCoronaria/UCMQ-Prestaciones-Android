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

public class MedicalServiceListActivity extends BaseDrawerActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ListMedicalServiceTabAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ListMedicalServiceTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    @Override
    protected @LayoutRes int getLayout() {
        return R.layout.activity_list_medical_service;
    }

    @Override
    protected BaseFragment getFragment() {
        return null;
    }


    @Override
    protected int getHeaderView() {
        return R.layout.activity_main_drawer_header;
    }

    @Override
    protected int getDrawerMenu() {
        return R.menu.activity_main_drawer;
    }

    public static Intent getStartIntent(Context context){
        return new Intent(context, MedicalServiceListActivity.class);
    }

    protected boolean showDisplayHomeAsUpEnabled() {
        return true;
    }

    protected boolean showHomeButtonEnable() {
        return true;
    }
}

