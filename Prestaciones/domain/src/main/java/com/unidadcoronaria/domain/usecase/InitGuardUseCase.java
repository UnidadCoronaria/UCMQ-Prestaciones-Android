package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.MobileObservation;
import com.unidadcoronaria.domain.transformer.MobileObservationTransformer;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class InitGuardUseCase extends UseCase<Void> {

    private final MobileObservationTransformer transformer = new MobileObservationTransformer();
    private List<MobileObservation> mobileObservations;
    private Integer guardId;

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().initGuard(transformer.transformToEntity(this.mobileObservations), this.guardId,new SuccessFailureCallBack<Void>() {
            @Override
            public void onSuccess(Void object) {
                BusProvider.getDefaultBus().post(new SuccessResponse());
            }

            @Override
            public void onFailure(String message) {
                BusProvider.getDefaultBus().post(new ErrorResponse());
            }
        });
    }

    public void setData(List<MobileObservation> mobileObservations, Integer guardId){
        this.mobileObservations = mobileObservations;
        this.guardId = guardId;
    }

    //region Inner Classes
    public static class SuccessResponse {

    }

    public static class ErrorResponse {

    }
    //endregion
}
