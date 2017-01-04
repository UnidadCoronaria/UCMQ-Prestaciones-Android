
package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Provider;
import com.unidadcoronaria.domain.transformer.ProviderTransformer;
import com.unidadcoronaria.prestaciones.data.entity.ProviderEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetProviderUseCase extends UseCase<List<Provider>> {

    private final ProviderTransformer transformer = new ProviderTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getProvider(new SuccessFailureCallBack<List<ProviderEntity>>() {
            @Override
            public void onSuccess(List<ProviderEntity> object) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object)));
            }

            @Override
            public void onFailure(String message) {
                GetProviderUseCase.super.onFailure(message);
            }
        });
    }

    //region Inner Classes
    public static class SuccessResponse {

        private List<Provider> list;

        public SuccessResponse(List<Provider> list) {
            this.list = list;
        }

        public List<Provider> getList() {
            return list;
        }
    }
    //endregion
}
