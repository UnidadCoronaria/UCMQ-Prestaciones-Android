package com.unidadcoronaria.prestaciones.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.DiagnosticFragment;
import com.unidadcoronaria.prestaciones.app.fragment.MedicamentFragment;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class DiagnosticActivity extends BaseActivity {

    public final static String MEDICAL_SERVICE_KEY = "MEDICAL_SERVICE_KEY";
    private DiagnosticFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.diagnostics));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_diagnostic;
    }

    @Override
    protected BaseFragment getFragment() {
        fragment = (DiagnosticFragment) DiagnosticFragment.newInstance();
        return fragment;
    }

    public static Intent getStartIntent(Context context, MedicalServiceResource medicalService){
        Intent intent = new Intent(context, DiagnosticActivity.class);
        intent.putExtra(MEDICAL_SERVICE_KEY, medicalService);
        return intent;
    }

    @Override
    protected Boolean locationEnabled() {
        return true;
    }
}
