package com.unidadcoronaria.prestaciones.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.MedicalServiceListFragment;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class MainActivity extends BaseNavActivity {


    @Override
    protected BaseFragment getFragment() {
        return  MedicalServiceListFragment.newInstance();
    }


    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}

