package com.unidadcoronaria.prestaciones.app.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.globallogic.recepcionvirtual.R;
import com.globallogic.recepcionvirtual.app.adapter.ViewPagerAdapter;
import com.globallogic.recepcionvirtual.app.fragments.LoginFragment;
import com.globallogic.recepcionvirtual.app.presenter.ListPresenter;
import com.globallogic.recepcionvirtual.util.GCMHelper;
import com.globallogic.recepcionvirtual.util.SharedPreferencesHelper;

public class ListMedicalServiceActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ListPresenter listPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listPresenter = new ListPresenter(this);
        setContentView(R.layout.activity_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_activity_list));
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
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
    protected void onResume() {
        super.onResume();
        listPresenter.getGuest();
    }

    @Override
    @CallSuper
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.visitorfragment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_close) {
            SharedPreferencesHelper.putString(this, LoginFragment.SHARED_PROPERTY_MAIL, null);
            SharedPreferencesHelper.putString(this, GCMHelper.PROPERTY_REG_ID, null);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}

