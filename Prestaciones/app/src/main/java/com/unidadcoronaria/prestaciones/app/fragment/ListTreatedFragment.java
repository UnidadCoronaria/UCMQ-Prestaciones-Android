package com.unidadcoronaria.prestaciones.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.globallogic.recepcionvirtual.R;
import com.globallogic.recepcionvirtual.app.ListGuestView;
import com.globallogic.recepcionvirtual.app.adapter.GuestAdapter;
import com.globallogic.recepcionvirtual.app.presenter.ListTreatedPresenter;
import com.globallogic.recepcionvirtual.domain.model.Guest;

import java.util.List;

public class ListTreatedFragment extends Fragment implements GuestAdapter.Callback , ListGuestView {

    ListTreatedPresenter presenter;
    protected  GuestAdapter mVisitorsAdapter;
    private SwipeRefreshLayout swipeContainer;


    public ListTreatedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter= new ListTreatedPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_treated, container, false);

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                swipeContainer.setRefreshing(false);
                Toast.makeText(ListTreatedFragment.this.getActivity(), "REFRESH", Toast.LENGTH_SHORT).show();
            }
        });

        mVisitorsAdapter = new GuestAdapter(this);
        RecyclerView listView = (RecyclerView) rootView.findViewById(R.id.listview_visitors);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(mVisitorsAdapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void receiveGuest(List<Guest> guests) {
        mVisitorsAdapter.addAll(guests);
    }

    @Override
    public void onGuestAttended(Guest guest) {
        mVisitorsAdapter.add(guest);
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

    @Override
    public void finish() {

    }

    @Override
    public void onGuestClick(Guest guest) {

    }
}


