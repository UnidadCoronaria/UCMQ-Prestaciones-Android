package com.unidadcoronaria.prestaciones.data.entity;

/**
 * Created by AGUSTIN.BALA on 01/01/2017.
 */

public class MedicalServiceResourceEntity extends BaseEntity {

    private Integer medicalServiceResourceId;
    private MedicalServiceEntity medicalService;
    private ResourceEntity resource;
    private Integer canceled;
    private char closedMedicalService;

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
}
