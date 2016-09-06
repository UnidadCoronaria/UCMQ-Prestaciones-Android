package com.unidadcoronaria.prestaciones.app.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceAddress;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.adapter.MedicalServiceAdapter;

import java.util.Arrays;

import butterknife.BindView;


public class ListMedicalServicePendingFragment extends BaseFragment implements MedicalServiceAdapter.Callback {

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    private MedicalServiceAdapter mAdapter;

    @BindView(R.id.list_medical_service)
    RecyclerView vListMedicalService;


    public ListMedicalServicePendingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootView = super.onCreateView(inflater, container, savedInstanceState);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(false);
                Toast.makeText(ListMedicalServicePendingFragment.this.getActivity(), "REFRESH", Toast.LENGTH_SHORT).show();
            }
        });

        MedicalService ms1 =  new MedicalService();
        MedicalServiceAddress msa1 = new MedicalServiceAddress();
        msa1.setStreet("Avellaneda 151 - Solano");
        ms1.setMedicalServiceAddress(msa1);
        ms1.setName("Mares");
        MedicalService ms2 =  new MedicalService();
        MedicalServiceAddress msa2 = new MedicalServiceAddress();
        msa2.setStreet("Sarmiento 414 - Quilmes");
        ms2.setMedicalServiceAddress(msa2);
        ms2.setName("Dolor de cabeza");

        vListMedicalService.setLayoutManager(new LinearLayoutManager(getActivity()));
        vListMedicalService.setHasFixedSize(true);
        mAdapter = new MedicalServiceAdapter(this, Arrays.asList(ms2,ms1,ms2,ms1,ms2,ms1,ms2,ms1,ms2,ms1));
        vListMedicalService.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_medical_service_pending;
    }

    @Override
    public void onMedicalServiceClick(MedicalService medicalService) {
        Toast.makeText(getActivity(), "Seleccionaste "+medicalService.getMedicalServiceAddress().getStreet(), Toast.LENGTH_SHORT).show();
    }
}


