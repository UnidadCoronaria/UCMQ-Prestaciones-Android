package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.domain.model.Watch;
import com.unidadcoronaria.domain.transformer.WatchTransformer;
import com.unidadcoronaria.prestaciones.data.entity.WatchEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetWatchUseCase extends UseCase<WatchEntity> {

    private final WatchTransformer transformer = new WatchTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getWatch(new SuccessFailureCallBack<WatchEntity>() {
            @Override
            public void onSuccess(WatchEntity watch) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(watch)));
            }

            @Override
            public void onFailure(String message) {
                GetWatchUseCase.super.onFailure(message);
            }
        });
    }

    //region Inner Classes
    public static class SuccessResponse {

        private Watch watch;

        public SuccessResponse(Watch watch) {
            this.watch = watch;
        }

        public Watch getWatch() {
            return watch;
        }
    }
    //endregion
}
