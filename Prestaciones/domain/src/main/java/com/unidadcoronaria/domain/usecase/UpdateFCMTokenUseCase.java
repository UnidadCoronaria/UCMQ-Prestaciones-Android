package com.unidadcoronaria.domain.usecase;

import android.content.Context;
import android.util.Log;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.transformer.MedicalServiceResourceTransformer;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class UpdateFCMTokenUseCase extends UseCase<MedicalServiceResource> {

    private String fcmToken;

    @Override
    public void execute(Context aContext) {
        Log.d(this.getClass().getSimpleName(), "Updating FCMTOKEN "+fcmToken);
        ApiClient.getInstance().updateFCMToken(new SuccessFailureCallBack<Void>() {
            @Override
            public void onSuccess(Void object) {
                BusProvider.getDefaultBus().post(new SuccessResponse());
            }

            @Override
            public void onFailure(String message) {
                BusProvider.getDefaultBus().post(new ErrorResponse());
            }
        }, fcmToken);
    }

    public void setData(String fcmToken){
        this.fcmToken = fcmToken;
    }

    //region Inner Classes
    public static class SuccessResponse {

    }

    public static class ErrorResponse {

    }
    //endregion
}
