package com.unidadcoronaria.prestaciones.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.google.android.gms.common.api.Status;
import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.SplashView;
import com.unidadcoronaria.prestaciones.app.SupplyView;
import com.unidadcoronaria.prestaciones.app.activity.MedicalServiceDetailActivity;
import com.unidadcoronaria.prestaciones.app.adapter.SupplyAdapter;
import com.unidadcoronaria.prestaciones.app.adapter.SupplyFilterAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.SplashPresenter;
import com.unidadcoronaria.prestaciones.app.presenter.SupplyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Agustin.Bala
 * @since  0.0.1
 */
public class SupplyFragment extends BaseFragment implements SupplyView, SupplyAdapter.Callback, SupplyFilterAdapter.Callback {



    //region Variables
    @BindView(R.id.fragment_supply_autocomplete)
    AppCompatAutoCompleteTextView vSupplyAutocomplete;

    @BindView(R.id.fragment_supply_list)
    RecyclerView vSupplyList;

    private SupplyAdapter adapter;
    private SupplyPresenter presenter;
    //endregions

    //region Constructors implementations
    public static BaseFragment newInstance() {
        return new SupplyFragment();
    }
    //endregion

    //region Lifecycle implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = new SupplyPresenter(this, getContext());
        vSupplyList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SupplyAdapter(this, new ArrayList<Supply>());
        vSupplyList.setAdapter(adapter);
        return view;
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_supply;
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
    public void onListSupplyRetrieved(List<Supply> supplyList) {
        final ArrayAdapter<Supply> filterAdapter = new ArrayAdapter<>
                (getContext(),android.R.layout.select_dialog_item, supplyList);
        vSupplyAutocomplete.setThreshold(3);
        vSupplyAutocomplete.setAdapter(filterAdapter);
        vSupplyAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Supply supply = filterAdapter.getItem(position);
                adapter.add(supply);
                vSupplyAutocomplete.setText("");
            }
        });

    }

    @Override
    public void displayError(String message) {

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
    public void onSupplyClick(Supply supply) {
        adapter.remove(supply);
    }

    @OnClick(R.id.fragment_supply_accept_button)
    protected void onAcceptButtonClick(View view){

    }

    @OnClick(R.id.fragment_supply_cancel_button)
    protected void onCancelButtonClick(View view){
        getActivity().finish();
    }
}