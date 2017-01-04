
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
public class GetMedicalServiceUseCase extends UseCase<MedicalServiceResource> {

    private final MedicalServiceResourceTransformer transformer = new MedicalServiceResourceTransformer();
    private Long medicalServiceId;

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getMedicalService(medicalServiceId,new SuccessFailureCallBack<MedicalServiceResourceEntity>() {
            @Override
            public void onSuccess(MedicalServiceResourceEntity object) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object)));
            }

            @Override
            public void onFailure(String message) {
                GetMedicalServiceUseCase.super.onFailure(message);
            }
        });
    }

    public void setData(Long medicalServiceId){
        this.medicalServiceId = medicalServiceId;
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
    //endregion
}
