package com.unidadcoronaria.domain.model.directions;

import com.unidadcoronaria.domain.model.BaseModel;

import java.util.List;

/**
 * Created by AGUSTIN.BALA on 12/18/2016.
 */

public class Route  extends BaseModel{

    private String distance;
    private List<Point> points;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
