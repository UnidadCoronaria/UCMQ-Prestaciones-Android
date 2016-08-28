package com.unidadcoronaria.domain.usecase;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public interface UseCaseCallback<T> {

    void onSuccess(T object);

    void onFailure(String message);
}
