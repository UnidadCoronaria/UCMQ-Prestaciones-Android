package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Guard;
import com.unidadcoronaria.domain.transformer.GuardTransformer;
import com.unidadcoronaria.prestaciones.data.entity.GuardEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class InitGuardUseCase extends UseCase<Guard> {

    private final GuardTransformer transformer = new GuardTransformer();
    private Guard guard;

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().initGuard(transformer.transformToEntity(this.guard), new SuccessFailureCallBack<GuardEntity>() {
            @Override
            public void onSuccess(GuardEntity Guard) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(Guard)));
            }

            @Override
            public void onFailure(String message) {
                InitGuardUseCase.super.onFailure(message);
            }
        });
    }

    public void setGuard(Guard Guard){
        this.guard = Guard;
    }

    //region Inner Classes
    public static class SuccessResponse {

        private Guard Guard;

        public SuccessResponse(Guard Guard) {
            this.Guard = Guard;
        }

        public Guard getGuard() {
            return Guard;
        }
    }
    //endregion
}
