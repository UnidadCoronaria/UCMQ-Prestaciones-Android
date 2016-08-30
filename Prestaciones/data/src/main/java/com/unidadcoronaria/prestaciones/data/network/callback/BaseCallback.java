package com.unidadcoronaria.prestaciones.data.network.callback;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class BaseCallback<E> implements Callback<E> {

    //region Properties
    protected final Transformer mapper;
    protected SuccessFailureCallBack callBack;
    //endregion

    //region Constructor
    public BaseCallback(SuccessFailureCallBack callBack, Transformer mapper) {
        this.callBack = callBack;
        this.mapper = mapper;
    }
    //endregion

    //region Callback Implementation
    @SuppressWarnings("unchecked")
    @Override
    public void onResponse(Response<E> response, Retrofit retrofit) {
        if (response.isSuccess()) {
            validateResponse(response);
        } else {
            // TODO parse error
            callBack.onFailure(null);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        callBack.onFailure(t.getMessage());
    }
    //endregion

    //region Protected Implementation
    @SuppressWarnings("unchecked")
    protected void validateResponse(Response<E> response) {
        callBack.onSuccess(mapper.transform(response.body()));
    }
    //endregion
}
