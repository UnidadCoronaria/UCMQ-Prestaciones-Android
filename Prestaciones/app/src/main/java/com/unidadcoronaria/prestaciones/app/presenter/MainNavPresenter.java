package com.unidadcoronaria.prestaciones.app.presenter;

import com.unidadcoronaria.prestaciones.app.MainDrawerView;
import com.unidadcoronaria.prestaciones.app.fragment.DeviceMessageFragment;
import com.unidadcoronaria.prestaciones.app.fragment.GuardFragment;
import com.unidadcoronaria.prestaciones.app.fragment.MedicalServiceListFragment;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MainNavPresenter extends BasePresenter<MainDrawerView> {



    public MainNavPresenter(MainDrawerView view) {
        super(view);
    }

    public void openMedicalServices() {
        view.showFragment(MedicalServiceListFragment.newInstance());
    }

    public void openInitWatch(){
        view.showFragment(GuardFragment.newInstance());
    }

    public void openMessages(){
        view.showFragment(DeviceMessageFragment.newInstance());
    }
}
