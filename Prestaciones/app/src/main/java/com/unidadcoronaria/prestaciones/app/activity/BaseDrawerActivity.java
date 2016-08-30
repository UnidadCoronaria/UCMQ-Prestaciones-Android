package com.unidadcoronaria.prestaciones.app.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;


import com.unidadcoronaria.prestaciones.R;

import butterknife.BindView;

/**
 * The base class for all activities that contains an NavigationDrawer
 *
 * @author Agustin.Bala
 * @since 0.0.1
 */
public abstract class BaseDrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout vDrawer;
    @BindView(R.id.nav_view)
    NavigationView vNavigationView;

    //region BaseActivity implementation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureDrawer();
    }

    @Override
    public void onBackPressed() {
        if (vDrawer.isDrawerOpen(GravityCompat.START)) {
            vDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected @LayoutRes int getLayout() {
        return R.layout.activity_base_with_drawer;
    }
    //endregion

    //region Private Implementation
    private void configureDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, vDrawer, vToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        vDrawer.addDrawerListener(toggle);
        toggle.syncState();

        int headerView = getHeaderView();
        if (headerView > 0) {
            setupHeader(vNavigationView.inflateHeaderView(headerView));
        }
        vNavigationView.inflateMenu(getDrawerMenu());
        vNavigationView.setNavigationItemSelectedListener(this);
    }
    //endregion

    //region Private Implementation
    @LayoutRes
    protected abstract int getHeaderView();

    protected abstract void setupHeader(View header);
    //endregion

    //region Abstract methods declarations
    protected abstract @MenuRes int getDrawerMenu();
    //endregion
}