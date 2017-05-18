package com.unidadcoronaria.prestaciones.data.entity.directions;

import java.util.List;

/**
 * Created by AGUSTIN.BALA on 12/17/2016.
 */

public class RouteEntity {

    private List<LegEntity> legs;

    public List<LegEntity> getLegs() {
        return legs;
    }

    public void setLegs(List<LegEntity> legs) {
        this.legs = legs;
    }
}
