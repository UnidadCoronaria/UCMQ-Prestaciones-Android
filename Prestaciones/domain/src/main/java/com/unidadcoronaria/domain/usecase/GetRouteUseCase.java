package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.directions.Polyline;
import com.unidadcoronaria.domain.model.directions.Route;
import com.unidadcoronaria.domain.transformer.RouteTransformer;
import com.unidadcoronaria.prestaciones.data.entity.directions.RouteResponseEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetRouteUseCase extends UseCase<List<Polyline>> {

    private String origin;
    private String destination;
    private final RouteTransformer transformer = new RouteTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getRoute(new SuccessFailureCallBack<RouteResponseEntity>() {
            @Override
            public void onSuccess(RouteResponseEntity object) {
                if(object.getRoutes() != null && object.getRoutes().size() > 0) {
                    BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(object.getRoutes().get(0))));
                }
            }

            @Override
            public void onFailure(String message) {
                BusProvider.getDefaultBus().post(new ErrorResponse());
            }
        },origin,destination);
    }

    public void setData(String origin, String destination){
        this.origin = origin;
        this.destination = destination;
    }

    //region Inner Classes
    public static class SuccessResponse {

        private Route mRoute;

        public SuccessResponse(Route route) {
            this.mRoute = route;
        }

        public Route getRoute() {
            return mRoute;
        }
    }

    //region Inner Classes
    public static class ErrorResponse {

    }
    //endregion
}
