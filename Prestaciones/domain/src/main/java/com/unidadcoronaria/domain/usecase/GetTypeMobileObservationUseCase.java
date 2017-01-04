package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Guard;
import com.unidadcoronaria.domain.model.TypeMobileObservation;
import com.unidadcoronaria.domain.transformer.TypeMobileObservationTransformer;
import com.unidadcoronaria.prestaciones.data.entity.TypeMobileObservationEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetTypeMobileObservationUseCase extends UseCase<TypeMobileObservation> {

    private final TypeMobileObservationTransformer transformer = new TypeMobileObservationTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getTypeMobileObservation(new SuccessFailureCallBack<List<TypeMobileObservationEntity>>() {
            @Override
            public void onSuccess(List<TypeMobileObservationEntity> typeMobileObservationEntityList) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(typeMobileObservationEntityList)));
            }

            @Override
            public void onFailure(String message) {
                GetTypeMobileObservationUseCase.super.onFailure(message);
            }
        });
    }

    //region Inner Classes
    public static class SuccessResponse {

        private List<TypeMobileObservation> typeMobileObservationList;

        public SuccessResponse( List<TypeMobileObservation> typeMobileObservationList) {
            this.typeMobileObservationList = typeMobileObservationList;
        }

        public List<TypeMobileObservation> getTypeMobileObservationList() {
            return typeMobileObservationList;
        }
    }
    //endregion
}
