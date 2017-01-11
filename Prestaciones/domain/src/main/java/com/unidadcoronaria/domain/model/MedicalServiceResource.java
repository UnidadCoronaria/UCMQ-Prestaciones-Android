package com.unidadcoronaria.domain.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AGUSTIN.BALA on 01/01/2017.
 */

public class MedicalServiceResource extends BaseModel implements Serializable{

    private Integer medicalServiceResourceId;
    private MedicalService medicalService;
    private Resource resource;
    private Integer canceled;
    private char closedMedicalService;
    private Integer currentState;
    private List<Integer> authorizedStates;

    public Integer getMedicalServiceResourceId() {
        return medicalServiceResourceId;
    }

    public void setMedicalServiceResourceId(Integer medicalServiceResourceId) {
        this.medicalServiceResourceId = medicalServiceResourceId;
    }

    public MedicalService getMedicalService() {
        return medicalService;
    }

    public void setMedicalService(MedicalService medicalService) {
        this.medicalService = medicalService;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
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
}
