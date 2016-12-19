package com.unidadcoronaria.prestaciones.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.MedicalServiceDetailFragment;

public class MedicalServiceDetailActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_medical_service_detail;
    }

    @Override
    protected BaseFragment getFragment() {
        return MedicalServiceDetailFragment.newInstance();
    }

    public static Intent getStartIntent(Context context){
        return new Intent(context, MedicalServiceDetailActivity.class);
    }

    protected boolean showDisplayHomeAsUpEnabled() {
        return true;
    }

    protected boolean showHomeButtonEnable() {
        return true;
    }
}

