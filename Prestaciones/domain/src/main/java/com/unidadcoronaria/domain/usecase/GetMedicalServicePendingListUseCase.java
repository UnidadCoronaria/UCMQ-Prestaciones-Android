package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.transformer.MedicalServiceResourceTransformer;
import com.unidadcoronaria.domain.transformer.MedicalServiceTransformer;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetMedicalServicePendingListUseCase extends UseCase<List<MedicalServiceResource>> {

    private final MedicalServiceResourceTransformer transformer = new MedicalServiceResourceTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getMedicalServicePendingList(new SuccessFailureCallBack<List<MedicalServiceResourceEntity>>() {
            @Override
            public void onSuccess(List<MedicalServiceResourceEntity> object) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object)));
            }

            @Override
            public void onFailure(String message) {
                BusProvider.getDefaultBus().post(new ErrorResponse());
            }
        });
    }

    //region Inner Classes
    public static class SuccessResponse {

        private List<MedicalServiceResource> mList;

        public SuccessResponse(List<MedicalServiceResource> list) {
            this.mList = list;
        }

        public List<MedicalServiceResource> getList() {
            return mList;
        }
    }

    public static class ErrorResponse {

    }
    //endregion
}
