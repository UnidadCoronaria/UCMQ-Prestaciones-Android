package com.unidadcoronaria.prestaciones.app.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The base class for all activities
 *
 * @author Agustin.Bala
 * @since 0.0.1
 */
public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    @Nullable
    Toolbar vToolbar;

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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    //endregion

    //region Abstract methods
    protected abstract @LayoutRes int getLayout();

    protected abstract BaseFragment getFragment();

    protected boolean showDisplayHomeAsUpEnabled() {
        return false;
    }

    protected boolean showHomeButtonEnable() {
        return false;
    }
    //endregion
}