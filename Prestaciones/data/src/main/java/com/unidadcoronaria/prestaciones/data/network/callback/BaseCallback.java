package com.unidadcoronaria.prestaciones.data.network.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class BaseCallback<E> implements Callback<E> {

    //region Properties
    protected SuccessFailureCallBack callBack;
    //endregion

    //region Constructor
    public BaseCallback(SuccessFailureCallBack callBack) {
        this.callBack = callBack;
    }
    //endregion

    //region Callback Implementation
    @SuppressWarnings("unchecked")
    @Override
    public void onResponse(Call<E> call, Response<E> response) {
        if (response.isSuccessful()) {
            validateResponse(response);
        } else {
            // TODO parse error
            callBack.onFailure(null);
        }
    }

    @Override
    public void onFailure(Call<E> call, Throwable t) {
        callBack.onFailure(t.getMessage());
    }
    //endregion

    //region Protected Implementation
    @SuppressWarnings("unchecked")
    protected void validateResponse(Response<E> response) {
        callBack.onSuccess(response.body());
    }
    //endregion
}
