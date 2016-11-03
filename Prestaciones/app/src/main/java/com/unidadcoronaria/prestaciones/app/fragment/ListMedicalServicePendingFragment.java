package com.unidadcoronaria.prestaciones.app.fragment;

import android.content.Intent;
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
import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.ListMedicalServiceView;
import com.unidadcoronaria.prestaciones.app.activity.MedicalServiceDetailActivity;
import com.unidadcoronaria.prestaciones.app.adapter.MedicalServiceAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.ListMedicalServicePendingPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;


public class ListMedicalServicePendingFragment extends BaseFragment implements MedicalServiceAdapter.Callback, ListMedicalServiceView {

    static final String MEDICAL_SERVICE_KEY = "medicalService";

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    private MedicalServiceAdapter mAdapter;

    @BindView(R.id.list_medical_service)
    RecyclerView vListMedicalService;

    private ListMedicalServicePendingPresenter presenter;


    public ListMedicalServicePendingFragment() {
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        presenter.getList();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootView = super.onCreateView(inflater, container, savedInstanceState);
        presenter = new ListMedicalServicePendingPresenter(this, getActivity());
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getList();
            }
        });



        vListMedicalService.setLayoutManager(new LinearLayoutManager(getActivity()));
        vListMedicalService.setHasFixedSize(true);
        mAdapter = new MedicalServiceAdapter(this, new ArrayList<MedicalService>());
        vListMedicalService.setAdapter(mAdapter);
        return rootView;
    }


    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_medical_service_pending;
    }

    @Override
    public void onMedicalServiceClick(MedicalService medicalService) {
        Intent intent = new Intent(this.getActivity(), MedicalServiceDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MEDICAL_SERVICE_KEY , medicalService);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        vProgress.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        vProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vProgress.setVisibility(View.GONE);
    }

    @Override
    public void onListRetrieved(List<MedicalService> list) {
        swipeContainer.setRefreshing(false);
        mAdapter.addAll(list);
    }
}


