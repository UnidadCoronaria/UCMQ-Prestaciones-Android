package com.unidadcoronaria.domain.model.directions;

import com.unidadcoronaria.domain.model.BaseModel;

/**
 * Created by AGUSTIN.BALA on 12/18/2016.
 */

public class Point extends BaseModel{

    private Double lat;
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
