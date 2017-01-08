package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;
import android.util.Log;

import com.unidadcoronaria.domain.model.DeviceMessage;
import com.unidadcoronaria.domain.usecase.GetDeviceMessageUseCase;
import com.unidadcoronaria.domain.usecase.SendDeviceMessageUseCase;
import com.unidadcoronaria.prestaciones.app.MessageView;
import com.unidadcoronaria.prestaciones.app.activity.event.OnUserChange;
import com.unidadcoronaria.prestaciones.util.SessionHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.Date;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class DeviceMessagePresenter extends BasePresenter<MessageView>  {

    private Context mContext;
    private GetDeviceMessageUseCase mGetDeviceMessageUseCase;
    private SendDeviceMessageUseCase mSendDeviceMessageUseCase;

    public DeviceMessagePresenter(MessageView view, Context context) {
        super(view);
        this.mContext = context;
        this.mGetDeviceMessageUseCase = new GetDeviceMessageUseCase();
        this.mSendDeviceMessageUseCase = new SendDeviceMessageUseCase();
    }

    public void getList(){
        view.showLoading();
        mGetDeviceMessageUseCase.setData(Integer.valueOf(SessionHelper.getGuardId()));
        mGetDeviceMessageUseCase.execute(mContext);
    }

    @Subscribe
    public void onMessageListReceived(GetDeviceMessageUseCase.SuccessResponse response){
        view.onMessageListReceived(response.getMessageList());
    }

    public void sendMessage(String message) {
        view.showLoading();
        mSendDeviceMessageUseCase.setData(Integer.valueOf(SessionHelper.getGuardId()), new DeviceMessage(message, new Date()));
        mSendDeviceMessageUseCase.execute(mContext);
    }

    @Subscribe
    public void onMessageSendReceived(SendDeviceMessageUseCase.SuccessResponse response){
        view.onMessageSendReceived(response.getDeviceMessage());
        view.hideLoading();
    }

    @Subscribe
    public void onUserChange(OnUserChange response){
        Log.i("DevicePresenter", "On user change");
        getList();
    }
}
