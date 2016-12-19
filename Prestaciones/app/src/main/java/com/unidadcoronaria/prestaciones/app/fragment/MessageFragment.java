package com.unidadcoronaria.prestaciones.app.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unidadcoronaria.domain.model.Message;
import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.MessageView;
import com.unidadcoronaria.prestaciones.app.adapter.MessageAdapter;
import com.unidadcoronaria.prestaciones.app.adapter.SupplyAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.MessagePresenter;
import com.unidadcoronaria.prestaciones.app.presenter.SupplyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class MessageFragment extends BaseFragment implements MessageView, MessageAdapter.Callback {

    @BindView(R.id.fragment_message_list)
    protected RecyclerView mMessageList;

    private MessageAdapter adapter;
    private MessagePresenter presenter;
    private static BaseFragment instance;

    //region Constructors implementations
    public static BaseFragment newInstance() {
        if(instance == null) {
            instance = new MessageFragment();
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
        presenter = new MessagePresenter(this, getContext());
        mMessageList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MessageAdapter(this, new ArrayList<Message>());
        mMessageList.setAdapter(adapter);
        presenter.getList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        getActivity().setTitle(R.string.main_drawer_messages);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_message;
    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public void showLoading() {
        vProgress.setVisibility(View.VISIBLE);
        mMessageList.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        vProgress.setVisibility(View.GONE);
        mMessageList.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMessageClick(Message message) {

    }

    @Override
    public void onMessageListReceived(List<Message> messageList) {
        adapter.addAll(messageList);
    }
}
