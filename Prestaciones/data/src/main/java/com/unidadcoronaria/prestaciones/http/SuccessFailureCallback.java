package com.unidadcoronaria.prestaciones.http;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface SuccessFailureCallback<T> {

    void onSuccess(T object);

    void onFailure(String message);
}
