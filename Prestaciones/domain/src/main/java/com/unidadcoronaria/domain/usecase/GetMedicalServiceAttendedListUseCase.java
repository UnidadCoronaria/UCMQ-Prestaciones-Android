package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.transformer.MedicalServiceResourceTransformer;
import com.unidadcoronaria.prestaciones.data.entity.MedicalServiceResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetMedicalServiceAttendedListUseCase extends UseCase<List<MedicalServiceResource>> {

    private final MedicalServiceResourceTransformer transformer = new MedicalServiceResourceTransformer();
    private Integer guardId;

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getMedicalServiceAttendedList(new SuccessFailureCallBack<List<MedicalServiceResourceEntity>>() {
            @Override
            public void onSuccess(List<MedicalServiceResourceEntity> object) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object)));
            }

            @Override
            public void onFailure(String message) {
                BusProvider.getDefaultBus().post(new ErrorResponse());
            }
        }, this.guardId);
    }

    public void setData(Integer guardId){
        this.guardId = guardId;
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
