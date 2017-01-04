package com.unidadcoronaria.prestaciones.app.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.MainDrawerView;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.presenter.MainNavPresenter;

import butterknife.BindView;

/**
 * The base class for all activities that contains an NavigationDrawer
 *
 * @author Agustin.Bala
 * @since 0.0.1
 */
public abstract class BaseNavActivity extends BaseActivity implements MainDrawerView {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView vNavigationView;

    private MainNavPresenter presenter;

    //region BaseActivity implementation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureNav();
        presenter = new MainNavPresenter(this);
    }

    private void configureNav() {
        vNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_medical_services:
                                presenter.openMedicalServices();
                                return true;
                            case R.id.nav_start_watch:
                                presenter.openInitWatch();
                                return true;
                            case R.id.nav_messages:
                                presenter.openMessages();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
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
    protected @LayoutRes
    int getLayout() {
        return R.layout.activity_base_with_nav;
    }

    @Override
    protected abstract BaseFragment getFragment();
    //endregion

    //region Abstract methods declarations

    @Override
    public void displayError(String message) {
        // Do nothing
    }

    @Override
    public void showLoading() {
        // Do nothing
    }

    @Override
    public void hideLoading() {
        // Do nothing
    }

    @Override
    public void showFragment(BaseFragment fragment){
         getSupportFragmentManager().beginTransaction().addToBackStack("")
                    .replace(R.id.activity_base_fragment, fragment).commit();
    }
    //endregion
}