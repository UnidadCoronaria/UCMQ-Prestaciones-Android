package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.directions.Point;
import com.unidadcoronaria.domain.model.directions.Route;
import com.unidadcoronaria.prestaciones.data.entity.directions.PointEntity;
import com.unidadcoronaria.prestaciones.data.entity.directions.RouteEntity;
import com.unidadcoronaria.prestaciones.data.entity.directions.StepEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class RouteTransformer implements Transformer<RouteEntity, Route> {

    private PointTransformer transformer = new PointTransformer();

    @Override
    public Route transform(RouteEntity object) {
        Route route = new Route();
        if(object.getLegs() != null && object.getLegs().size() > 0 && object.getLegs().get(0).getDistance() != null) {
            route.setDistance(object.getLegs().get(0).getDistance().getText());
        }
        if(object.getLegs() != null && object.getLegs().size() > 0 && object.getLegs().get(0).getSteps() != null &&
                object.getLegs().get(0).getSteps().size() > 0) {
            List<PointEntity> pointEntityList = new ArrayList<>();
            for (StepEntity entity : object.getLegs().get(0).getSteps()) {
                pointEntityList.add(entity.getStart_location());
                pointEntityList.add(entity.getEnd_location());
            }
            route.setPoints(transformer.transform(pointEntityList));
        }
        return route;
    }

    @Override
    public List<Route> transform(List<RouteEntity> object) {
        List<Route> mList = new ArrayList<>();
        for (RouteEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }
}
