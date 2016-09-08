package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.transformer.MedicalServiceTransformer;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetMedicalServicePendingListUseCase extends UseCase<List<MedicalService>> {

    private final MedicalServiceTransformer transformer = new MedicalServiceTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getMedicalServicePendingList(new SuccessFailureCallBack<List<MedicalServiceEntity>>() {
            @Override
            public void onSuccess(List<MedicalServiceEntity> object) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object)));
            }

            @Override
            public void onFailure(String message) {
                GetMedicalServicePendingListUseCase.super.onFailure(message);
            }
        });
    }

    //region Inner Classes
    public static class SuccessResponse {

        private List<MedicalService> mList;

        public SuccessResponse(List<MedicalService> list) {
            this.mList = list;
        }

        public List<MedicalService> getList() {
            return mList;
        }
    }
    //endregion
}
