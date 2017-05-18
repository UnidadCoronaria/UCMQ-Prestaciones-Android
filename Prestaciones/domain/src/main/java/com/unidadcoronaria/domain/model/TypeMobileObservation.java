package com.unidadcoronaria.domain.model;

/**
 * Created by AGUSTIN.BALA on 01/02/2017.
 */

public class TypeMobileObservation extends BaseModel {

    private Integer typeMobileObservationId;
    private String name;
    private String currentObservation;
    private Boolean currentState;
    private Integer currentViewState = 0;

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

    public String getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(String currentObservation) {
        this.currentObservation = currentObservation;
    }

    public Boolean getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Boolean currentState) {
        this.currentState = currentState;
    }

    public Integer getCurrentViewState() {
        return currentViewState;
    }

    public void setCurrentViewState(Integer currentViewState) {
        this.currentViewState = currentViewState;
    }
}
