package com.unidadcoronaria.domain.model;

/**
 * Created by AGUSTIN.BALA on 01/02/2017.
 */

public class TypeMobileObservation extends BaseModel {

    private Integer typeMobileObservationId;
    private String name;

    public Integer getTypeMobileObservationId() {
        return typeMobileObservationId;
    }

    public void setTypeMobileObservationId(Integer typeMobileObservationId) {
        this.typeMobileObservationId = typeMobileObservationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
