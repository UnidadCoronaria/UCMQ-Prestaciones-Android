package com.unidadcoronaria.domain.model;

import java.io.Serializable;

/**
 * Created by AGUSTIN.BALA on 3/22/2017.
 */

public class MedicalServiceCallReason implements Serializable {

    private Integer medicalServiceCallReasonId;
    private CallReason callReasonMedicalService;

    public Integer getMedicalServiceCallReasonId() {
        return medicalServiceCallReasonId;
    }

    public void setMedicalServiceCallReasonId(Integer medicalServiceCallReasonId) {
        this.medicalServiceCallReasonId = medicalServiceCallReasonId;
    }

    public CallReason getCallReasonMedicalService() {
        return callReasonMedicalService;
    }

    public void setCallReasonMedicalService(CallReason callReasonMedicalService) {
        this.callReasonMedicalService = callReasonMedicalService;
    }
}
