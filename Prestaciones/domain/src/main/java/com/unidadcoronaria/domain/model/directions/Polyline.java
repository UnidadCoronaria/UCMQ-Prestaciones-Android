package com.unidadcoronaria.domain.model.directions;

import com.unidadcoronaria.domain.model.BaseModel;

/**
 * Created by AGUSTIN.BALA on 12/17/2016.
 */

public class Polyline extends BaseModel{

    private String points;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
