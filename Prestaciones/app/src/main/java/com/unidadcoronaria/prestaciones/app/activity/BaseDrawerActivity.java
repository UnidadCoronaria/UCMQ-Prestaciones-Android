package com.unidadcoronaria.prestaciones.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.MainDrawerView;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.presenter.MainDrawerPresenter;

import butterknife.BindView;

/**
 * The base class for all activities that contains an NavigationDrawer
 *
 * @author Agustin.Bala
 * @since 0.0.1
 */
public abstract class BaseDrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, MainDrawerView {

    View vHeader;
    @BindView(R.id.drawer_layout)
    DrawerLayout vDrawer;
    @BindView(R.id.nav_view)
    NavigationView vNavigationView;
    TextView vResourceName;
    TextView vResourcePerson;

    private MainDrawerPresenter presenter;

    //region BaseActivity implementation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureDrawer();
        presenter = new MainDrawerPresenter(this);
        presenter.getData();
    }

    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @CallSuper
    public void onPause() {
        super.onPause();
        presenter.onPause();
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

    @Override
    protected BaseFragment getFragment() {
        return null;
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

    protected void setupHeader(View header) {
        vHeader = header;
        vResourceName = (TextView) header.findViewById(R.id.activity_main_drawer_header_resource_name);
        vResourcePerson = (TextView) header.findViewById(R.id.activity_main_drawer_header_resource_person);
    }

    //endregion

    //region Abstract methods declarations
    protected abstract @MenuRes int getDrawerMenu();

    @Override
    public void onResourceRetrieved(Resource resource) {
        vResourceName.setText("Movil 51");
        vResourcePerson.setText("Rolando Gomez");
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
    //endregion
}