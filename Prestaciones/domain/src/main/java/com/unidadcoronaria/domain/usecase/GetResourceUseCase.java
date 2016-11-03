
package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Resource;
import com.unidadcoronaria.domain.transformer.ResourceTransformer;
import com.unidadcoronaria.prestaciones.data.entity.ResourceEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetResourceUseCase extends UseCase<Resource> {

    private final ResourceTransformer transformer = new ResourceTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getResource(new SuccessFailureCallBack<ResourceEntity>() {
            @Override
            public void onSuccess(ResourceEntity object) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object)));
            }

            @Override
            public void onFailure(String message) {
                GetResourceUseCase.super.onFailure(message);
            }
        });
    }

    //region Inner Classes
    public static class SuccessResponse {

        private Resource resource;

        public SuccessResponse(Resource resource) {
            this.resource = resource;
        }

        public Resource getResource() {
            return resource;
        }
    }
    //endregion
}
