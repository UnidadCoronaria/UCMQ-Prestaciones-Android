package com.unidadcoronaria.prestaciones.data.entity;

/**
 * Created by AGUSTIN.BALA on 3/22/2017.
 */

public class MedicalServiceCallReasonEntity {

    private Integer medicalServiceCallReasonId;
    private CallReasonEntity callReasonMedicalService;

    public Integer getMedicalServiceCallReasonId() {
        return medicalServiceCallReasonId;
    }

    public void setMedicalServiceCallReasonId(Integer medicalServiceCallReasonId) {
        this.medicalServiceCallReasonId = medicalServiceCallReasonId;
    }

    public CallReasonEntity getCallReasonMedicalService() {
        return callReasonMedicalService;
    }

    public void setCallReasonMedicalService(CallReasonEntity callReasonMedicalService) {
        this.callReasonMedicalService = callReasonMedicalService;
    }
}
