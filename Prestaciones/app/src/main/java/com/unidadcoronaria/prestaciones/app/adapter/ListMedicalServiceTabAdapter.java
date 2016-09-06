package com.unidadcoronaria.prestaciones.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.unidadcoronaria.prestaciones.app.fragment.ListMedicalServiceAttendedFragment;
import com.unidadcoronaria.prestaciones.app.fragment.ListMedicalServicePendingFragment;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ListMedicalServiceTabAdapter  extends FragmentStatePagerAdapter {

    public ListMedicalServiceTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ListMedicalServicePendingFragment();
            case 1:
                return new ListMedicalServiceAttendedFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return  "Pendientes";
        }
        return "Atendidos";
    }

    @Override
    public int getCount() {
        return 2;
    }

}
