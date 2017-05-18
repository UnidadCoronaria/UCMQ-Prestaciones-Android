package com.unidadcoronaria.prestaciones.app.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.unidadcoronaria.domain.model.DeviceMessage;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.MessageView;
import com.unidadcoronaria.prestaciones.app.adapter.DeviceMessageAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.DeviceMessagePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class DeviceMessageFragment extends BaseFragment implements MessageView {

    @BindView(R.id.fragment_message_list)
    protected RecyclerView mMessageList;

    @BindView(R.id.fragment_messages_error_container)
    View vErrorContainer;

    @BindView(R.id.fragment_message_send)
    protected View vSendButton;

    @BindView(R.id.fragment_message_text)
    protected EditText vText;

    @BindView(R.id.fragment_message_container)
    protected View vContainer;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;

    private DeviceMessageAdapter adapter;
    private DeviceMessagePresenter presenter;
    private static BaseFragment instance;
    private Handler mHandler;

    //region Constructors implementations
    public static BaseFragment newInstance() {
        return new DeviceMessageFragment();
    }
    //endregion


    //region Lifecycle implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = new DeviceMessagePresenter(this, getContext());
        LinearLayoutManager llm =new LinearLayoutManager(getContext());
        llm.setStackFromEnd(true);
        mMessageList.setLayoutManager(llm);
        adapter = new DeviceMessageAdapter(new ArrayList<DeviceMessage>());
        mMessageList.setAdapter(adapter);
        presenter.getList();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getList();
                swipeContainer.setRefreshing(true);
            }
        });
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_message;
    }

    @Override
    public void displayError(String message) {
        vText.setText("");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.message_sent_error)).setPositiveButton(getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });
        builder.create().show();
        vSendButton.setClickable(true);
    }

    @Override
    public void showLoading() {
        vProgress.setVisibility(View.VISIBLE);
        vText.setVisibility(View.GONE);
        vSendButton.setVisibility(View.GONE);
        vContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        vProgress.setVisibility(View.GONE);
        vContainer.setVisibility(View.VISIBLE);
        mMessageList.setVisibility(View.VISIBLE);
        vSendButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMessageListReceived(List<DeviceMessage> messageList) {
        adapter.addAll(messageList);
        swipeContainer.setRefreshing(false);
        vErrorContainer.setVisibility(View.GONE);
        vProgress.setVisibility(View.GONE);
        swipeContainer.setVisibility(View.GONE);
        vText.setVisibility(View.VISIBLE);
        mMessageList.setVisibility(View.VISIBLE);
        vSendButton.setVisibility(View.VISIBLE);
        vContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMessageSendReceived(DeviceMessage message) {
        vSendButton.setClickable(true);
        vText.setText("");
        vSendButton.setVisibility(View.VISIBLE);
        vText.setVisibility(View.VISIBLE);
        mMessageList.setVisibility(View.VISIBLE);
       /* InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(vText.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
        */adapter.add(message);
        if(adapter.getItemCount() >= 1) {
            mMessageList.smoothScrollToPosition(adapter.getItemCount() - 1);
        }
      /*  AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.message_sent)).setPositiveButton(getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });
         builder.create().show();*/
    }

    @Override
    public void showListError() {
        vErrorContainer.setVisibility(View.VISIBLE);
        mMessageList.setVisibility(View.GONE);
        vContainer.setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);
        vProgress.setVisibility(View.GONE);
        swipeContainer.setVisibility(View.VISIBLE);
        vText.setVisibility(View.GONE);
        vSendButton.setVisibility(View.GONE);
    }

    @OnClick(R.id.fragment_message_send)
    public void onSendClick(View view){
        if(vText.getText() != null && !vText.getText().toString().isEmpty()) {
            vSendButton.setClickable(false);
            presenter.sendMessage(vText.getText().toString());
        }
    }
}
