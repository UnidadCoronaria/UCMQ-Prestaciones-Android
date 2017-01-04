package com.unidadcoronaria.prestaciones.app.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.unidadcoronaria.domain.model.TypeMobileObservation;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.adapter.MobileObservationAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.GuardPresenter;
import com.unidadcoronaria.prestaciones.app.viewpager.GuardView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Agustin.Bala
 * @since  0.0.1
 */
public class GuardFragment extends BaseFragment implements GuardView {



    //region Variables
    @BindView(R.id.fragment_watch_list)
    protected RecyclerView vRecyclerView;
    @BindView(R.id.fragment_watch_button)
    protected FloatingActionButton vButton;

    private MobileObservationAdapter mAdapter;
    private GuardPresenter presenter;
    private List<TypeMobileObservation> typeMobileObservations;
    private LinearLayoutManager mLayoutManager;
    private static GuardFragment instance;
    //endregions

    //region Constructors implementations
    public static BaseFragment newInstance() {
        if(instance == null) {
            instance = new GuardFragment();
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
        presenter = new GuardPresenter(this, getContext());
        mLayoutManager = new LinearLayoutManager(getActivity());
        vRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MobileObservationAdapter(getActivity(),this);
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
    public void onMobileObservationRetrieved(List<TypeMobileObservation> typeMobileObservations) {
        this.typeMobileObservations = typeMobileObservations;
        mAdapter.add(typeMobileObservations);
    }

    @OnClick(R.id.fragment_watch_button)
    public void onSaveButton(){
       //TODO
    }

    @Override
    public void onMobileObservationItemsCompleted() {
        vButton.show();
    }

    @Override
    public void onWatchMobileObservationItemsIncompleted() {
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
                //TODO
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

}