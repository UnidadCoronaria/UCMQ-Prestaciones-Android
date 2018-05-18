package com.unidadcoronaria.prestaciones.data.network.callback;


import retrofit2.Response;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class EmptyResultEntityCallback<E> extends BaseCallback<E> {

    public EmptyResultEntityCallback(SuccessFailureCallBack callBack) {
        super(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void validateResponse(Response<E> response) {
        callBack.onSuccess(response.body());
    }
}
