package com.unidadcoronaria.prestaciones.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.MedicamentFragment;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicamentActivity extends BaseActivity {

    public final static String MEDICAL_SERVICE_KEY = "MEDICAL_SERVICE_KEY";
    private MedicamentFragment fragment;

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
        fragment = (MedicamentFragment) MedicamentFragment.newInstance();
        return fragment;
    }

    public static Intent getStartIntent(Context context, MedicalService medicalService){
        Intent intent = new Intent(context, MedicamentActivity.class);
        intent.putExtra(MEDICAL_SERVICE_KEY, medicalService);
        return intent;
    }

    @Override
    protected Boolean locationEnabled() {
        return true;
    }
}
