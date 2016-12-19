package com.unidadcoronaria.prestaciones.app.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.adapter.ListMedicalServiceTabAdapter;

/**
 * Created by AGUSTIN.BALA on 11/23/2016.
 */

public class MedicalServiceListFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ListMedicalServiceTabAdapter viewPagerAdapter;
    private static MedicalServiceListFragment instance;

    //region Constructors implementations
    public static BaseFragment newInstance() {
        if(instance == null) {
            instance = new MedicalServiceListFragment();
        }
        return instance;
    }
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootView = super.onCreateView(inflater, container, savedInstanceState);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPagerAdapter = new ListMedicalServiceTabAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle(R.string.main_drawer_medical_services);
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_list_medical_service;
    }
}
