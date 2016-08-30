package com.unidadcoronaria.prestaciones.data.network.callback;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface SuccessFailureCallBack<T> {
    void onSuccess(T object);

    void onFailure(String message);
}
