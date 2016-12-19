package com.unidadcoronaria.prestaciones.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.Menu;
import android.view.MenuInflater;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.SupplyFragment;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class SupplyActivity extends BaseActivity {

    public final static String MEDICAL_SERVICE_KEY = "MEDICAL_SERVICE_KEY";
    private SupplyFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.watch_entities));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_supply;
    }

    @Override
    protected BaseFragment getFragment() {
        fragment = (SupplyFragment) SupplyFragment.newInstance();
        return fragment;
    }

    public static Intent getStartIntent(Context context, MedicalService medicalService){
        Intent intent = new Intent(context, SupplyActivity.class);
        intent.putExtra(MEDICAL_SERVICE_KEY, medicalService);
        return intent;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_supply, menu);
        return true;
    }*/
}
