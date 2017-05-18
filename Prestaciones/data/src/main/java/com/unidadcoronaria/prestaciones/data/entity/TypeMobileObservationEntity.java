package com.unidadcoronaria.prestaciones.data.entity;

/**
 * Created by AGUSTIN.BALA on 01/02/2017.
 */

public class TypeMobileObservationEntity extends BaseEntity {

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
