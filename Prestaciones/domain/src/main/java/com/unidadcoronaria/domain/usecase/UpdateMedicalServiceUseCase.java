package com.unidadcoronaria.domain.usecase;

import android.content.Context;

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
public class UpdateMedicalServiceUseCase extends UseCase<MedicalServiceResource> {

    private final MedicalServiceResourceTransformer transformer = new MedicalServiceResourceTransformer();
    private Integer medicalServiceId;
    private Integer state;

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().closeMedicalServiceResource(new SuccessFailureCallBack<MedicalServiceResourceEntity>() {
            @Override
            public void onSuccess(MedicalServiceResourceEntity medicalServiceEntity) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(medicalServiceEntity)));
            }

            @Override
            public void onFailure(String message) {
                BusProvider.getDefaultBus().post(new ErrorResponse());
            }
        },medicalServiceId, state);
    }

    public void setData(Integer medicalServiceId, Integer state){
        this.medicalServiceId = medicalServiceId;
        this.state = state;
    }

    //region Inner Classes
    public static class SuccessResponse {

        private MedicalServiceResource medicalService;

        public SuccessResponse(MedicalServiceResource medicalService) {
            this.medicalService = medicalService;
        }

        public MedicalServiceResource getMedicalService() {
            return medicalService;
        }
    }

    public static class ErrorResponse {

    }
        //endregion
}
