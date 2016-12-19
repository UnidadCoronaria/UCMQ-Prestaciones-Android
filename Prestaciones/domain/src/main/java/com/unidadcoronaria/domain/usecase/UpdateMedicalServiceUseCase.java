package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.Watch;
import com.unidadcoronaria.domain.transformer.MedicalServiceTransformer;
import com.unidadcoronaria.domain.transformer.WatchTransformer;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.entity.WatchEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class UpdateMedicalServiceUseCase extends UseCase<MedicalService> {

    private final MedicalServiceTransformer transformer = new MedicalServiceTransformer();
    private MedicalService medicalService;

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().updateMedicalService(transformer.transformToEntity(this.medicalService), new SuccessFailureCallBack<MedicalServiceEntity>() {
            @Override
            public void onSuccess(MedicalServiceEntity medicalServiceEntity) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(medicalServiceEntity)));
            }

            @Override
            public void onFailure(String message) {
                UpdateMedicalServiceUseCase.super.onFailure(message);
            }
        });
    }

    public void setMedicalService(MedicalService medicalService){
        this.medicalService = medicalService;
    }

    //region Inner Classes
    public static class SuccessResponse {

        private MedicalService medicalService;

        public SuccessResponse(MedicalService medicalService) {
            this.medicalService = medicalService;
        }

        public MedicalService getMedicalService() {
            return medicalService;
        }
    }
    //endregion
}
