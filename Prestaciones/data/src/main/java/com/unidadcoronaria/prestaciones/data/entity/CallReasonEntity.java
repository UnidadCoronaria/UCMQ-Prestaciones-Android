package com.unidadcoronaria.prestaciones.data.entity;

/**
 * Created by AGUSTIN.BALA on 3/22/2017.
 */

public class CallReasonEntity {

    private Integer callReasonMedicalServiceId;
    private String name;

    public Integer getCallReasonMedicalServiceId() {
        return callReasonMedicalServiceId;
    }

    public void setCallReasonMedicalServiceId(Integer callReasonMedicalServiceId) {
        this.callReasonMedicalServiceId = callReasonMedicalServiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
