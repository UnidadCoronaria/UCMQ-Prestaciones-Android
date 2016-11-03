package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.domain.transformer.ResourceTransformer;
import com.unidadcoronaria.domain.transformer.SupplyTransformer;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.entity.SupplyEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetSupplyUseCase extends UseCase<List<Supply>> {

    private final SupplyTransformer transformer = new SupplyTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getSupply(new SuccessFailureCallBack<List<SupplyEntity>>() {
            @Override
            public void onSuccess(List<SupplyEntity> list) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(list)));
            }

            @Override
            public void onFailure(String message) {
                GetSupplyUseCase.super.onFailure(message);
            }
        });
    }

    //region Inner Classes
    public static class SuccessResponse {

        private List<Supply> supply;

        public SuccessResponse(List<Supply> supply) {
            this.supply = supply;
        }

        public List<Supply> getSupply() {
            return supply;
        }
    }
    //endregion
}
