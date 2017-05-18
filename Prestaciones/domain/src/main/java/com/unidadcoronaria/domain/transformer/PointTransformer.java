package com.unidadcoronaria.domain.transformer;

import com.unidadcoronaria.domain.model.directions.Point;
import com.unidadcoronaria.domain.model.directions.Polyline;
import com.unidadcoronaria.prestaciones.data.entity.directions.PointEntity;
import com.unidadcoronaria.prestaciones.data.entity.directions.PolylineEntity;
import com.unidadcoronaria.prestaciones.data.network.callback.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class PointTransformer implements Transformer<PointEntity, Point> {

    @Override
    public Point transform(PointEntity object) {
        Point point = new Point();
        point.setLat(object.getLat());
        point.setLng(object.getLng());
        return point;
    }

    @Override
    public List<Point> transform(List<PointEntity> object) {
        List<Point> mList = new ArrayList<>();
        for (PointEntity entity: object) {
            mList.add(transform(entity));
        }
        return mList;
    }
}
