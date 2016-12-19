package com.unidadcoronaria.prestaciones.app.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.domain.model.Watch;
import com.unidadcoronaria.domain.model.WatchItem;
import com.unidadcoronaria.domain.usecase.InitWatchUseCase;
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
    @BindView(R.id.fragment_watch_button)
    protected FloatingActionButton vButton;

    private WatchAdapter mAdapter;
    private WatchPresenter presenter;
    private Watch watch;
    private LinearLayoutManager mLayoutManager;
    private static WatchFragment instance;
    //endregions

    //region Constructors implementations
    public static BaseFragment newInstance() {
        if(instance == null) {
            instance = new WatchFragment();
        }
        return instance;
    }
    //endregion

    //region Lifecycle implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        vButton.hide();
        presenter = new WatchPresenter(this, getContext());
        mLayoutManager = new LinearLayoutManager(getActivity());
        vRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WatchAdapter(getActivity(),this);
        vRecyclerView.setAdapter(mAdapter);
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
        getActivity().setTitle(R.string.main_drawer_start_watch);
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
        vProgress.setVisibility(View.VISIBLE);
        vRecyclerView.setVisibility(View.GONE);
        vButton.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        vProgress.setVisibility(View.GONE);
        vRecyclerView.setVisibility(View.VISIBLE);
        vButton.setVisibility(View.VISIBLE);
        vButton.hide();
    }


    @Override
    public void onWatchRetrieved(Watch watch) {
        this.watch = watch;
        mAdapter.add(watch.getItemList());
    }

    @OnClick(R.id.fragment_watch_button)
    public void onSaveButton(){
        watch.setItemList(mAdapter.getList());
        for (WatchItem item: watch.getItemList()) {
            if(!item.getStatus()){
                showNoteDialog();
                return;
            }
        }
        initWatch(watch);
    }

    @Override
    public void onWatchItemsCompleted() {
        vButton.show();
    }

    @Override
    public void onWatchItemsIncompleted() {
        vButton.hide();
    }


    private void showNoteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_note, null);
        // Set up the input
        final EditText input = (EditText) view.findViewById(R.id.view_note_text);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(view);

        // Set up the buttons
        builder.setPositiveButton( getActivity().getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                watch.setNote(input.getText().toString());
                initWatch(watch);
            }
        });
        builder.setNegativeButton(getActivity().getString(R.string.button_close) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void initWatch(Watch watch){
        InitWatchUseCase initWatchUseCase = new InitWatchUseCase();
        initWatchUseCase.setWatch(watch);
        initWatchUseCase.execute(getActivity());
    }

}