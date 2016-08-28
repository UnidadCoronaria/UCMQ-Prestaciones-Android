package com.unidadcoronaria.domain.usecase;


import android.content.Context;

import com.globallogic.recepcionvirtual.domain.BusProvider;
import com.globallogic.recepcionvirtual.domain.model.GeneralUseCaseError;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public abstract class UseCase<T> implements UseCaseCallback<T> {

    @Override
    public void onSuccess(T object) {
        // Do nothing by default
    }

    @Override
    public void onFailure(String errorMessage) {
        BusProvider.getDefaultBus().post(new GeneralUseCaseError(errorMessage));
    }

    public abstract void execute(Context aContext);
}
