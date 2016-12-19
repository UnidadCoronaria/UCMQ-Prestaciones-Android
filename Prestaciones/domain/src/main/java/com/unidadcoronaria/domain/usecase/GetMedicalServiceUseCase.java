
package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.Message;
import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.domain.transformer.MedicalServiceTransformer;
import com.unidadcoronaria.domain.transformer.MessageTransformer;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.entity.MessageEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetMedicalServiceUseCase extends UseCase<MedicalService> {

    private final MedicalServiceTransformer transformer = new MedicalServiceTransformer();
    private Long medicalServiceId;

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getMedicalService(medicalServiceId,new SuccessFailureCallBack<MedicalServiceEntity>() {
            @Override
            public void onSuccess(MedicalServiceEntity object) {
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
