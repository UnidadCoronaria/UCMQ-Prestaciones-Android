package com.unidadcoronaria.prestaciones.app.activity;

import android.content.Context;
import android.content.Intent;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.SupplyFragment;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class SupplyActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment getFragment() {
        return SupplyFragment.newInstance();
    }

    public static Intent getStartIntent(Context context){
        return new Intent(context, SupplyActivity.class);
    }
}
