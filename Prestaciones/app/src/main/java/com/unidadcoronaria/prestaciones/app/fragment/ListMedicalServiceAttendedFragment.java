package com.unidadcoronaria.prestaciones.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.ListMedicalServiceView;
import com.unidadcoronaria.prestaciones.app.activity.MedicalServiceDetailActivity;
import com.unidadcoronaria.prestaciones.app.adapter.MedicalServiceAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.ListMedicalServiceAttendedPresenter;
import com.unidadcoronaria.prestaciones.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ListMedicalServiceAttendedFragment extends BaseFragment implements MedicalServiceAdapter.Callback, ListMedicalServiceView {

    public static final String MEDICAL_SERVICE_KEY = "medicalService";

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.swipe_container2)
    SwipeRefreshLayout swipeContainer2;
    @BindView(R.id.fragment_attended_list_error_container)
    View vErrorContainer;
    private MedicalServiceAdapter mAdapter;

    @BindView(R.id.list_medical_service)
    RecyclerView vListMedicalService;

    private ListMedicalServiceAttendedPresenter presenter;
    private Parcelable mListState;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        if (mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
        }
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
        presenter = new ListMedicalServiceAttendedPresenter(this, getActivity());
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getList();
            }
        });
        swipeContainer2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getList();
            }
        });
        mLayoutManager = new LinearLayoutManager(getActivity());
        vListMedicalService.setLayoutManager(mLayoutManager);
        vListMedicalService.setHasFixedSize(true);
        mAdapter = new MedicalServiceAdapter(this, new ArrayList<MedicalServiceResource>());
        vListMedicalService.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_medical_service_attended;
    }


    @Override
    public void displayError(String message) {
        //Toast.makeText(getActivity(),  message , Toast.LENGTH_SHORT).show();
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
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        // Save list state

        mListState = mLayoutManager.onSaveInstanceState();
        bundle.putParcelable("adapter", mListState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null)
            mListState = savedInstanceState.getParcelable("adapter");
    }

    @Override
    public void onListRetrieved(List<MedicalServiceResource> list) {
        swipeContainer.setRefreshing(false);
        swipeContainer2.setRefreshing(false);
        mAdapter.addAll(list);
        vListMedicalService.setVisibility(View.VISIBLE);
        vErrorContainer.setVisibility(View.GONE);
    }

    @Override
    public void onListError() {
        if(mAdapter.getItemCount() > 0){
            vErrorContainer.setVisibility(View.GONE);
            vListMedicalService.setVisibility(View.VISIBLE);
        } else {
            vErrorContainer.setVisibility(View.VISIBLE);
            vListMedicalService.setVisibility(View.GONE);
        }
        vProgress.setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);
        swipeContainer2.setRefreshing(false);
    }

    @Override
    public void onMedicalServiceClick(MedicalServiceResource medicalService) {
        Intent intent = new Intent(this.getActivity(), MedicalServiceDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.MEDICAL_SERVICE_OBJECT_KEY , medicalService);
        bundle.putString(Constants.MEDICAL_SERVICE_KEY , medicalService.getMedicalServiceResourceId().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}


