package com.unidadcoronaria.prestaciones.app.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.domain.model.WatchItem;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.SupplyView;
import com.unidadcoronaria.prestaciones.app.adapter.SupplyAdapter;
import com.unidadcoronaria.prestaciones.app.adapter.SupplyFilterAdapter;
import com.unidadcoronaria.prestaciones.app.adapter.WatchAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.SupplyPresenter;
import com.unidadcoronaria.prestaciones.app.presenter.WatchPresenter;
import com.unidadcoronaria.prestaciones.app.viewpager.WatchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Agustin.Bala
 * @since  0.0.1
 */
public class WatchFragment extends BaseFragment implements WatchView {


    //region Variables
    @BindView(R.id.fragment_watch_list)
    protected RecyclerView vRecyclerView;

    private WatchAdapter mAdapter;
    private WatchPresenter presenter;
    //endregions

    //region Constructors implementations
    public static BaseFragment newInstance() {
        return new WatchFragment();
    }
    //endregion

    //region Lifecycle implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = new WatchPresenter(this, getContext());
        vRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new WatchAdapter(getActivity(),this);
        vRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_watch;
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
    public void displayError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick(R.id.fragment_watch_accept_button)
    protected void onAccept(View view){
        List<WatchItem> watchItemList = mAdapter.getList();
    }

    @OnClick(R.id.fragment_watch_cancel_button)
    protected void onCancel(View view){

    }

}