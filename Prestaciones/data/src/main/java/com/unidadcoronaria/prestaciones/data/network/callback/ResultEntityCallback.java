package com.unidadcoronaria.prestaciones.data.network.callback;


import java.util.ArrayList;
import java.util.List;

import retrofit.Response;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class ResultEntityCallback<E> extends BaseCallback<E> {

    public ResultEntityCallback(SuccessFailureCallBack callBack, Transformer mapper) {
        super(callBack, mapper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void validateResponse(Response<E> response) {
        if (response.body() != null) {
            callBack.onSuccess(mapper.transform(response.body()));
        } else {
            //TODO Parse errors
            callBack.onFailure(null);
        }
    }
}
