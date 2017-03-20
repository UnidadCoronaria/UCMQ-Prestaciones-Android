package com.unidadcoronaria.prestaciones.app.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unidadcoronaria.domain.model.MobileObservation;
import com.unidadcoronaria.domain.model.TypeMobileObservation;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.adapter.MobileObservationAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.GuardPresenter;
import com.unidadcoronaria.prestaciones.app.viewpager.GuardView;
import com.unidadcoronaria.prestaciones.util.SessionHelper;

import java.util.ArrayList;
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
    @BindView(R.id.fragment_watch_error_container)
    View vErrorContainer;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.fragment_watch_container)
    View vContainer;

    private MobileObservationAdapter mAdapter;
    private GuardPresenter presenter;
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

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getList();
            }
        });
        presenter.getList();
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
        getActivity().setTitle(R.string.main_drawer_start_watch);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void displayError(String message) {
        // Nothing to do
    }

    public void onWatchError(){
        vErrorContainer.setVisibility(View.VISIBLE);
        swipeContainer.setVisibility(View.VISIBLE);
        vRecyclerView.setVisibility(View.GONE);
        vButton.setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);
        vContainer.setVisibility(View.GONE);
    }


    @Override
    public void showLoading() {
        vProgress.setVisibility(View.VISIBLE);
        vRecyclerView.setVisibility(View.GONE);
        vButton.setVisibility(View.GONE);
        vContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        vProgress.setVisibility(View.GONE);
    }


    @Override
    public void onMobileObservationRetrieved(List<TypeMobileObservation> typeMobileObservations) {
        mAdapter = new MobileObservationAdapter(getActivity(), this);
        mAdapter.add(typeMobileObservations);
        vRecyclerView.setAdapter(mAdapter);
        vErrorContainer.setVisibility(View.GONE);
        swipeContainer.setVisibility(View.GONE);
        vRecyclerView.setVisibility(View.VISIBLE);
        vContainer.setVisibility(View.VISIBLE);
        swipeContainer.setRefreshing(false);
    }

    @OnClick(R.id.fragment_watch_button)
    public void onSaveButton() {
        showConfirmDialog();
    }

    @Override
    public void onMobileObservationItemsCompleted() {
        vButton.show();
    }

    @Override
    public void onWatchMobileObservationItemsIncompleted() {
        vButton.hide();
    }

    @Override
    public void onGuardInit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("La guardia se inició correctamente");
        // Set up the buttons
        builder.setPositiveButton( getActivity().getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAdapter.add(new ArrayList<TypeMobileObservation>());
                vRecyclerView.setAdapter(mAdapter);
                presenter.getList();
            }
        });
        builder.show();
    }


    private void showConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Va a iniciar una guardia con "+ SessionHelper.getUsername()+" como responsable.\nSi esto no es correcto, por favor, cambie el usuario y vuelva a intentarlo");
        // Set up the buttons
        builder.setPositiveButton( getActivity().getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<TypeMobileObservation> list = mAdapter.getList();
                List<MobileObservation> mobileObservations = new ArrayList<>();
                for (TypeMobileObservation typeMobileObservation : list) {
                    MobileObservation mobileObservation = new MobileObservation();
                    mobileObservation.setObservation(typeMobileObservation.getCurrentObservation());
                    mobileObservation.setTypeMobileObservation(typeMobileObservation);
                    mobileObservations.add(mobileObservation);
                }
                presenter.initGuard(mobileObservations);
            }
        });
        builder.setNegativeButton(getActivity().getString(R.string.button_cancel) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void onGuardInitError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("La guardia no pudo iniciarse correctamente, por favor intentelo más tarde");
        // Set up the buttons
        builder.setPositiveButton( getActivity().getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onMobileObservationItemsCompleted();
            }
        });
        builder.show();
    }


}