package com.unidadcoronaria.prestaciones.data.entity;

import java.util.List;

/**
 * Created by AGUSTIN.BALA on 01/01/2017.
 */

public class MedicalServiceResourceEntity extends BaseEntity {

    private Integer medicalServiceResourceId;
    private MedicalServiceEntity medicalService;
    private ResourceEntity resource;
    private Integer canceled;
    private char closedMedicalService;
    private Integer currentState;
    private List<Integer> authorizedStates;
    private List<MedicalServiceCallReasonEntity> medicalServiceCallReason;
    private String planDetail;

    public Integer getMedicalServiceResourceId() {
        return medicalServiceResourceId;
    }

    public void setMedicalServiceResourceId(Integer medicalServiceResourceId) {
        this.medicalServiceResourceId = medicalServiceResourceId;
    }

    public MedicalServiceEntity getMedicalService() {
        return medicalService;
    }

    public void setMedicalService(MedicalServiceEntity medicalService) {
        this.medicalService = medicalService;
    }

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
        this.canceled = canceled;
    }

    public char getClosedMedicalService() {
        return closedMedicalService;
    }

    public void setClosedMedicalService(char closedMedicalService) {
        this.closedMedicalService = closedMedicalService;
    }

    public Integer getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }

    public List<Integer> getAuthorizedStates() {
        return authorizedStates;
    }

    public void setAuthorizedStates(List<Integer> authorizedStates) {
        this.authorizedStates = authorizedStates;
    }

    public List<MedicalServiceCallReasonEntity> getMedicalServiceCallReason() {
        return medicalServiceCallReason;
    }

    public void setMedicalServiceCallReason(List<MedicalServiceCallReasonEntity> medicalServiceCallReason) {
        this.medicalServiceCallReason = medicalServiceCallReason;
    }

    public String getPlanDetail() {
        return planDetail;
    }

    public void setPlanDetail(String planDetail) {
        this.planDetail = planDetail;
    }
}
