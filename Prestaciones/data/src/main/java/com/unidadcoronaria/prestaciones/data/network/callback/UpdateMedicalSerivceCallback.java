package com.unidadcoronaria.prestaciones.data.network.callback;

import com.unidadcoronaria.prestaciones.data.network.ApiClient;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.Call;

/**
 * @author AGUSTIN.BALA
 * @since 4.16
 */

public class UpdateMedicalSerivceCallback<E> extends ResultEntityCallback<E> {

    public UpdateMedicalSerivceCallback(SuccessFailureCallBack callBack) {
        super(callBack);
    }

    @Override
    public void onFailure(Call<E> call, Throwable t) {
        if(t instanceof IOException){
            EventBus.getDefault().post(new ApiClient.ConnectionError());
        }
        super.onFailure(call,t);
    }
}
