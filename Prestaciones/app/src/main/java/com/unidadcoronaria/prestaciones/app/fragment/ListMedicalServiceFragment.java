package com.unidadcoronaria.prestaciones.app.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unidadcoronaria.prestaciones.R;


public class ListMedicalServiceFragment extends BaseFragment {

    private SwipeRefreshLayout swipeContainer;


    public ListMedicalServiceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootView = super.onCreateView(inflater, container, savedInstanceState);

        //swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                swipeContainer.setRefreshing(false);
                Toast.makeText(ListMedicalServiceFragment.this.getActivity(), "REFRESH", Toast.LENGTH_SHORT).show();
            }
        });

        /*RecyclerView listView = (RecyclerView) rootView.findViewById(R.id.listview_visitors);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(mVisitorsAdapter);*/
        return rootView;
    }

    @Override
    protected int makeContentViewResourceId() {
        return 0;
    }

}


