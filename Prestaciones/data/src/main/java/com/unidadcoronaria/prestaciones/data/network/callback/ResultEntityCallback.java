package com.unidadcoronaria.prestaciones.data.network.callback;


import retrofit2.Response;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ResultEntityCallback<E> extends BaseCallback<E> {

    public ResultEntityCallback(SuccessFailureCallBack callBack) {
        super(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void validateResponse(Response<E> response) {
        if (response.body() != null) {
            callBack.onSuccess(response.body());
        } else {
            callBack.onFailure(null);
        }
    }
}
